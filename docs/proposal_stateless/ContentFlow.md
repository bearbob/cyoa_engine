# Content Flow - From creating to displaying a page

## Adding a page

1. Page is added via admin endpoints (without options)
2. Check if an option exists that reference this new page
   1. For each such option find the page where it is appended
   2. For each such page create a new entry in the catalogue with the events and items adapted
      1. If the same events and items already exist for this page, use this key instead
   3. Create an entry in navigation_catalogue for each source-target-pair and add the navigation_option_id

### Notes

A page has two different status: DRAFT and PUBLISHED

## Show page

1. Parse key, find page in `page_catalogue`
2. Find navigation_options for page
   1. For each option, given the current key and navigation_option_id, find the target key in `navigation_catalogue`
   2. Set the URL for this option to the target