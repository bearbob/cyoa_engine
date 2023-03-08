# Database Tables

* page - Contains the unprocessed content of a page in default language
  * Columns id, state_delta, raw_content
* translations - Contains a content translation for a given page
  * Columns id, page_id, locale
* navigation_option
  * Each option: 
    * belongs to a specific page A
    * references a single page B (with A != B usually, but not restricted)
  * Can contain conditions
* items 
  * Columns: id, comment
* events
  * Columns: id, comment
* state_items - Relation between a state ID and item ID(s)
  * Columns id, state_id, item_id, amount
* state_event - Relation between a state ID and event ID(s)
* state
  * Columns: id, event_hash, item_hash
* user - Simple list of created users with their identification key
* history - Tracks which userkey was used with which page and state at which time

A call to the API will contain an id that is build from: page+userkey+statekey (see `IdGeneration.md`).

## Items and Events

Items and Events are pretty similar with two differences:
1. There can be multiple items of the same type
2. In contrast to items, events can only be added and are never removed

Because of these two differences and potential other differences in the future, they are treated as
different entities.

## Handling state

A state key indicates which events and items are activated/possessed for the given state.
This means a state X with <X;EventA>, <X;EventB> and <X;ItemD;amount> has two active events and one item.

In the database, the statekey is the ID of a given state. 
It is stored along with a hash, which is used to quickly decide if a state is already stored or not.

When a change to a state is necessary (e.g. new event is active) we can first check if such
a state already exists:
* build the hash for the state in question
* check if another entry in the `state` table has the same hash values
and if such  a state does not exist: 
* create a new entry in `state` with new ID and the previously computed hash values
* create new entries in `state_items` and `state_events`

### Building the Hash Values for a state

* Build the event hash
  * Get all IDs connected to the state
  * Order them alphabetical in ascending order
  * Concatenate all these IDs into a single string, using `;` as delimiter
  * Build the SHA2 hash of the string (https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25)

* Build the item hash
  * Get all IDs and amounts connected to the state
  * Order the result set alphabetical by the IDs in ascending order
  * Concatenate all these IDs into a single string using the pattern `id.amount;`
  * Build the SHA2 hash of the string (https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25)