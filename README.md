# Server part
./server
Build it using "mvn clean install" before any other steps.
Then run main class "GameServerApplication" from IDE.
This part is not interactive.

# Observing client
./observing-client
Shows players from randomly choosed 32 *32 area of game field  as described in requirements.

Run main class "ObservingClientApplication" from IDE


# Update client
./update-client
Used for updating player's tasks as described in requirements.
Controlled with text commands. Commands described in application prompt.

Run main class "UpdateClientApplication" from IDE

# Requirements
Copy of requirement is here ./Online_test_New_TODO.docx

# TODO
Persistance is not implement yet. In case of server restart data will be lost.
