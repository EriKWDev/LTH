/*
 * Shows user info from local pwfile.
 *
 * Usage: userinfo username
 */

#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include "pwdblib.h" /* include header declarations for pwdblib.c */

/* Define some constants. */
#define USERNAME_SIZE (32)
#define NOUSER (-1)

volatile sig_atomic_t stop;

void inthand(int signum)
{
  stop = 1;
}

void print_info(const struct pwdb_passwd *p)
{
  // struct pwdb_passwd *p = pwdb_getpwnam(username);
  printf("Name: %s\n", p->pw_name);
  printf("Passwd: %s\n", p->pw_passwd);
  printf("Uid: %u\n", p->pw_uid);
  printf("Gid: %u\n", p->pw_gid);
  printf("Real name: %s\n", p->pw_gecos);
  printf("Home dir: %s\n", p->pw_dir);
  printf("Shell: %s\n", p->pw_shell);
}

void read_username(char *username)
{
  printf("login: ");
  fgets(username, USERNAME_SIZE, stdin);

  /* remove the newline included by getline() */
  username[strlen(username) - 1] = '\0';
}

int main(int argc, char **argv)
{
  signal(SIGINT, inthand);

  while (!stop)
  {
    char username[USERNAME_SIZE];

    /*
     * Write "login: " and read user input. Copies the username to the
     * username variable.
     */
    read_username(username);

    char *password;
    password = getpass("password: ");

    struct pwdb_passwd *p = pwdb_getpwnam(username);

    /*
     * If there is no entry matching the username, continue (restart..)
     */
    if (p == NULL)
    {
      printf("\nUnknown user or incorrect password.\n");
      continue;
    }

    if (p->pw_failed > 5)
    {
      printf("\nUser '%s' has been locked out due to too many failed authentication attempts.\n", username);
      printf("Please contact your system administrator to get this resolved.\n\n");
      continue;
    }

    /*
     * If passwords don't match, increase pw_failed, save and continue (restart...)
     */
    if (strcmp(password, p->pw_passwd) != 0)
    {
      p->pw_failed++;
      pwdb_update_user(p);
      printf("\nUnknown user or incorrect password.\n");
      continue;
    }

    // If none of the guarad statements above falied, the user has been authenticated

    printf("\nUser authenticated successfully!\n");

    p->pw_failed = 0;
    p->pw_age++;
    pwdb_update_user(p);

    if (p->pw_age > 10)
    {
      printf("\nYou must change your password! It is too old!\n");
    }

    // Print user information (not required by the task)
    // print_info(p);

    return 0;
  }

  system("pause");

  return 1;
}
