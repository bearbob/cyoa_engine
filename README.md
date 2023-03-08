<p align="center">
<img src="https://user-images.githubusercontent.com/7290445/223841570-4994b5e0-facd-4127-856d-dba4f13bc386.png#gh-light-mode-only">
<img src="https://user-images.githubusercontent.com/7290445/223841566-35776e35-d6b0-4f09-b015-c92255250ec8.png#gh-dark-mode-only">
</p>

---

# CYOA Engine

This service provides the infrastructure for [Choose Your Own Adventure](https://en.wikipedia.org/wiki/Choose_Your_Own_Adventure)-style games.
Users can read and play the story by using individually created page keys, that render the content.

### Built With

* Spring Boot
* Docker
* Postgres
* Kotlin

## Getting Started

### Building And Running

```bash
docker-compose up -d
./gradlew bootRun
```

#### Rebuild Database
```bash
docker-compose down
docker volume rm coya_engine_db
docker-compose up -d
```

### Linter
This project uses [ktlint](https://github.com/pinterest/ktlint) for consistent formatting.
You can automatically fix most format errors.
```bash
./gradlew ktlintFormat
```

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. 
Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. 
You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.

<!-- CONTACT -->
## Contact

TripleTwenty 
- Twitter [@tripletwenty_](https://twitter.com/tripletwenty_)
- Mastodon [@tripletwenty@det.social](https://det.social/web/@tripletwenty)
- Website [tripletwenty.net](https://tripletwenty.net)

Project Link: [https://github.com/bearbob/coya_engine](https://github.com/bearbob/coya_engine)

## Notes

### I Own My Mistakes

The name was supposed to be the shorty for "Choose your own adventure".
Coya.
I used this as a tag for these projects for years before I realized my mistake.
Since it is already names this way, I won't change it anymore.
But rest assured that I am shamefully aware.
