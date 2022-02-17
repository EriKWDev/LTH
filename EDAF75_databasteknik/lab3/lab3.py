from bottle import route, run, post, response, get, request
import sqlite3
from pprint import pprint
import random

from platformdirs import user_cache_dir

db = sqlite3.connect("movies.sqlite")
db.execute("PRAGMA foreign_key = ON;")

# for row in db.execute(
#     """
#     SELECT * FROM customers
#     """
# ):
#     print(row)


@get("/ping")
def ping():
    return "pong"


@post("/reset")
def reset():
    c = db.cursor()
    for table in ["customers", "theaters", "screenings", "movies", "tickets"]:
        c.execute(f"DELETE FROM {table}")
    db.commit()

    c.execute(
        """
        INSERT INTO theaters (theater_name, capacity)
        VALUES
          ("Kino", 10),
          ("Regal", 16),
          ("Skandia", 100);
        """
    )
    db.commit()

    response.status = 200


@post("/users")
def users():
    c = db.cursor()
    try:
        c.execute(
            """
        INSERT INTO customers (customer_name, username, password)
        VALUES
          (?, ?, ?)
        RETURNING username;
        """,
            [request.json["fullName"], request.json["username"], request.json["pwd"]],
        )
    except:
        pass

    found = c.fetchone()
    db.commit()

    if not found:
        response.status = 400
        return ""
    else:
        response.status = 201
        (username,) = found
        return f"/users/{username}"


@post("/movies")
def movies():
    c = db.cursor()
    try:
        c.execute(
            """
        INSERT INTO movies (imdb, title, year, running_time)
        VALUES
          (?, ?, ?, ?)
        RETURNING imdb;
        """,
            [
                request.json["imdbKey"],
                request.json["title"],
                request.json["year"],
                random.randint(60, 160),
            ],
        )
    except:
        pass

    found = c.fetchone()
    db.commit()

    if not found:
        response.status = 400
        return ""
    else:
        response.status = 201
        (imdb,) = found
        return f"/movies/{imdb}"


@get("/movies")
def get_movies():
    movies = []
    try:
        c = db.cursor()

        if len(request.query) > 0:
            if "year" in request.query and "title" not in request.query:
                movies = c.execute(
                    """
              SELECT imdb, title, year
              FROM movies 
              WHERE (year = ?)
              """,
                    [request.query["year"]],
                )
            elif "title" in request.query and "year" not in request.query:
                movies = c.execute(
                    """
              SELECT imdb, title, year
              FROM movies 
              WHERE (title = ?)
              """,
                    [request.query["title"]],
                )
            elif "title" in request.query and "year" in request.query:
                movies = c.execute(
                    """
              SELECT imdb, title, year
              FROM movies 
              WHERE (year = ? AND title = ?)
              """,
                    [request.query["year"], request.query["title"]],
                )
        else:
            movies = c.execute(
                """
            SELECT imdb, title, year
            FROM movies
            """
            )

    except Exception as e:
        print(e)
        pass

    data = []
    for row in movies:
        (imdb, title, year) = row
        data.append({"imdbKey": imdb, "title": title, "year": year})

    return {"data": data}


@get("/movies/<imdb>")
def get_movies_imdb(imdb):
    movies = []
    try:
        c = db.cursor()
        movies = c.execute(
            """
        SELECT imdb, title, year
        FROM movies
        WHERE imdb = ?
        """,
            [imdb],
        )

    except:
        pass

    data = []
    for row in movies:
        (imdb, title, year) = row
        data.append({"imdbKey": imdb, "title": title, "year": year})

    return {"data": data}


@post("/performances")
def performances():
    c = db.cursor()
    try:
        c.execute(
            """
          SELECT theater_id
          FROM theaters
          WHERE theater_name = ?
          """,
            [request.json["theater"]],
        )
        found = c.fetchone()
        (theater_id,) = found

        start_time = request.json["date"] + " " + request.json["time"]
        c.execute(
            """
        INSERT INTO screenings (start_time, imdb, theater_id)
        VALUES
          (?, ?, ?)
        RETURNING screening_id;
        """,
            [
                start_time,
                request.json["imdbKey"],
                theater_id,
            ],
        )
    except Exception as e:
        print(e)
        pass

    found = c.fetchone()
    db.commit()

    if not found:
        response.status = 400
        return ""
    else:
        response.status = 201
        (screening_id,) = found
        return f"/performances/{screening_id}"


@get("/performances")
def get_performances():
    performances = []

    c = db.cursor()
    try:
        performances = c.execute(
            """
            SELECT start_time, title, theater_name, theater_id, screening_id, year
            FROM screenings
            JOIN movies using (imdb)
            JOIN theaters using (theater_id)
            """
        )

    except Exception as e:
        print(e)

        return

    data = []
    for performance in list(performances):
        (start_time, title, theater_name, theater_id, screening_id, year) = performance

        date = start_time.split(" ")[0]
        time = start_time.split(" ")[1]

        try:
            c.execute(
                """
                SELECT capacity - count(ticket_id) AS remaining
                FROM screenings
                JOIN theaters USING (theater_id)
                LEFT OUTER JOIN tickets USING (screening_id)
                WHERE screening_id = ?
                """,
                [screening_id],
            )
        except Exception as e:
            print(e)

        found = c.fetchone()
        (remaining,) = found
        data.append(
            {
                "performanceId": screening_id,
                "date": date,
                "startTime": time,
                "title": title,
                "year": year,
                "theater": theater_name,
                "remainingSeats": remaining,
            }
        )
    return {"data": data}


@post("/tickets")
def post_tickets():
    c = db.cursor()
    try:
        c.execute(
            """
        SELECT *
        FROM customers
        WHERE username = ? AND password = ? 
        """,
            [request.json["username"], request.json["pwd"]],
        )

        user = c.fetchone()
        (user_id, user_real_name, username, password) = user

        if user is None:
            response.status = 400

            return "Wrong user credentials"

    except Exception as e:
        print(e)
        response.status = 501

        return "User error"

    try:
        c.execute(
            """
        SELECT capacity - count(ticket_id) AS remaining
        FROM screenings
        JOIN theaters USING (theater_id)
        LEFT OUTER JOIN tickets USING (screening_id)
        WHERE screening_id = ?
          """,
            [request.json["performanceId"]],
        )

        (remaining,) = c.fetchone()

        if remaining <= 0:
            raise Exception("No tickets left")

        c.execute(
            f"""
          INSERT INTO tickets (screening_id, customer_id)
          VALUES
            (?, ?)
          RETURNING ticket_id;
        """,
            [request.json["performanceId"], user_id],
        )
    except Exception as e:
        print(e)
        response.status = 400

        return "No tickets left"

    found = c.fetchone()
    db.commit()

    if not found:
        response.status = 400
        return "Error"

    response.status = 201
    (ticket_id,) = found
    return f"/tickets/{ticket_id}"


@get("/users/<username>/tickets")
def get_username_tickets(username):
    tickets = []
    try:
        c = db.cursor()

        tickets = c.execute(
            """
          SELECT title, start_time, year, theater_name, count(ticket_id) AS number_of_tickets
          FROM tickets
          JOIN customers USING (customer_id)
          JOIN screenings USING (screening_id)
          JOIN theaters USING (theater_id)
          JOIN movies USING (imdb)
          WHERE username = ?
          GROUP BY screening_id
        """,
            [username],
        )
    except Exception as e:
        print(e)
        response.status = 400
        return "Error"

    data = []
    for ticket in tickets:
        (title, date_time, year, theater_name, number_of_tickets) = ticket

        the_split = date_time.split(" ")

        data.append(
            {
                "date": the_split[0],
                "startTime": the_split[1],
                "theater": theater_name,
                "title": title,
                "year": year,
                "nbrOfTickets": number_of_tickets,
            }
        )

    response.status = 200
    return {"data": data}


run(host="localhost", port=7007)
