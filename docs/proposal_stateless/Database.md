# Database Tables

* page - Contains the unrendered content of a page
* navigation_option
  * Each option belongs to a specific page and references to a single page
  * Can contain conditions
* items
* events
* state_items - Relation between an ID and items
* state_event - Relation between an ID and events
* user - Simple list of created users with their identification key
* history - Tracks which userkey was used with which page and state at which time

A call to the API will contain a id that is build from page+userkey+statekey.

## Handling state

A statekey indicates which events and items are activated/possessed for the given state.
This means a state X with <X;EventA>, <X;EventB> and <X;ItemD;amount> has two active events and one item.
When a change to a state is necessary (e.g. new event is active) we can first check if such
an state already exists:
* (TODO)
and if such  a state does not exists, copy X with new ID Y and add another row for the new event.