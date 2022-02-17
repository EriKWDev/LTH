# 1. Entity sets:
- movie
- theater
- ticket
- screening
- customer

# 2. Relationships
[See drawing](https://lucid.app/lucidchart/62573868-64da-492e-94e3-929dbc04b6f7/edit?docId=62573868-64da-492e-94e3-929dbc04b6f7&shared=true&page=0_0&invitationId=inv_17da5902-f30b-4120-88f9-f85999ddffa6#)

# 3. Find attributes
[See drawing](https://lucid.app/lucidchart/62573868-64da-492e-94e3-929dbc04b6f7/edit?docId=62573868-64da-492e-94e3-929dbc04b6f7&shared=true&page=0_0&invitationId=inv_17da5902-f30b-4120-88f9-f85999ddffa6#) 

# 4. Identify keys
### primary keys
- customer_id
- ticket_id
- theater_id
- movie: imdb

### Foreign keys
- ticket: customer_id and screening_id

## a. Which relationships have natural keys? 
*customer*: username is unique 
*movie*: imdb and title+year
*theater*: name 

## b. Is there a risk that any of the natural keys will ever change? 
*theater* name can change 
*customer*: username

## c. are there any weak entity sets? 
screening is a weak entity. We assume that each theater has multiple room. 
There can therefore be multiple screenings with the same start time.

## d. which relations do you want to use as an invented key? Why? 
Ticket has an invented key: id 
We want a customer ID, in case they change their username 
Theater in case it changes its name.

# 6. Convert the E/R model to a relational model
## Describe your model:
movie(_imdb_, title, year, running_time)
screening(_screening_id_, /theater_id/, /imdb/, start_time) 
ticket(_ticket_id_, /screening_id/, /customer_id/) 
customer(_customer_id_, username, name, password) 
theater(_theater_id_, name, capacity) 

# 7. There are at least two ways of keeping track of the number of seats for each performance - describe them

1. Capacity minus count(tickets), for that screening. That is a "ledger" that keeps track of the tickets. 
  - Pros: no attribute we need to update. Less redundancy. 
  - Cons: We have to calculate count(tickets) everytime a ticket is sold.
2. An attribute in screening that keeps track of the remaining capacity for that screening 
  - Pros: Less computations needed when querying. 
  - Cons: More redundancy and disk space used.
3. Create tickets with customer_id = null. When a ticket is sold update the customer_id
  - Pros: Cannot sell more tickets than there are seats? (could happen in case update queries fail or are too slow in previous methods)
  - Cons: Lots of junk in database "complicated" "buy" query

