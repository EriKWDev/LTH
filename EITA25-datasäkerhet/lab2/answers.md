# Answers for Prep. Assignments for Lab 2

## 1. What's the format of `/etc/passwd`?
The format of /etc/passwd consists of one line per user.
Each line contains colon-separated values with the following format:
  
  name:password:UID:GID:GECOS:directory:shell
      ^
      | 
      |       / Passwords can also contain a `x` which indicates
      |_______| that the password is encrypted and stored inside
              \ the file `/etc/shadow`

## 2. What's different from the format in `pwfile` and `/etc/passwd`?
One line from `pwfile` looks like this:

  goofy:X2iOq64zClyug:2006:2006:Goofy:/home/goofy:/bin/bash:0:0

which seems to follow the format:

  name :password?    :UID :GUID:GECOS:directory  :shell    :n:m

In my `/etc/passwd` file, `password` is marked with a `x` everywhere
indicating that it is encrypted inside `/etc/shadow`. According to the
struct defined in `pwdlib.h`, the last numbers `n` and `m` seem to indicate
n: `number of contiguous unsuccessful logins` and m: `number of successful logins`

## 3. Implement mylogin.c #1
See `old_mylogin.c`

## 4. Implement mylogin.c #2
See `mylogin.c`

## 5. Understand `openshell_demo.c`
Done

## 6. Which commands are used to change an owner and permissions of a file?
The command that can be used for this is called `chmod` ('Change file mode bits')
as well as `chown` ('Change owner')

## 7. Read about ACL. Which commands are used to set and read ACL in Linux?
`ACL` stands for `Access Control List`. You can set the default ACLs for a file or directory
with `setfacl`, and they can be retrieved using `getfacl`