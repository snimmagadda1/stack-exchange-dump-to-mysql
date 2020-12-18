<h1 align="center">stackexchange-dump-to-mysql 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000" />
  <a href="LICENSE.md" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

> A quick pipeline to import [Stack Exchange XML dump](https://archive.org/details/stackexchange) data to a relational db

### 🏠 [TODO](https://s11a.com)

## Install

```sh
mvn clean package
```

## Usage

The `schema-base.sql` must be executed on an output schema. `app.datasource.xxx` properties should be updated
accordingly. Metrics job/task metadata by defualt are output to an in-memory HSQL DB which can be overridden with
the `spring.datasource.xxx` properties.

```sh
mvn spring-boot:run
```

## Run tests

```sh
mvn test
```

## Author

👤 **Sai Nimmagadda**

* Website: https://s11a.com
* Github: [@snimmagadda1](https://github.com/snimmagadda1)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to
check [issues page](https://github.com/snimmagadda1/stackexchange-dump-to-mysql/issues).

## 📝 License

Copyright © 2020 [Sai Nimmagadda](https://github.com/snimmagadda1).<br />
This project is [MIT](LICENSE.md) licensed.

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_