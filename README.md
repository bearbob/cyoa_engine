# cyoa_engine

## About The Project

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
docker volume rm cyoa_engine_db
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
