# ADR 0001 - Stateless Flow

This folder contains notes for the design decision to make the application stateless from the perspective of a user.
Each page will be served with a state, which holds the current items and events the user has acquired/seen.

From the perspective of the application it is stateless, because each call will result in the same content being delivered.
The result is only bound to the calling URL, not any change that is kept in memory/database.

This decision was made to enable users to go back to any point in their journey (if they have the link)
without the need of a complex save-point or navigation system.

It is more complex than just the IDs of the "pages" as with a physical book, because the goal of this engine
is to support more complex scenarios, where the player character can collect items and experience certain event, 
which shape the possible options of future pages.