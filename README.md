<h1 align="center">stack-exchange-dump-to-mysql 👋</h1>
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

Before the pipeline is run, the `schema-base.sql` must be executed on the desired output schema. This will initialize
the tables and create necessary indices for the data dump.

Run with docker (taking care to pass the required app.datasource.xxx and spring.datasource.xxx properties as env vars):

```sh
docker run -e APP_DATASOURCE_URL=XXXXX -e ... snimmagadda/stacke-batch-mysql:latest 
```

To run from source, `app.datasource.xxx` properties should be updated accordingly. Metrics job/task metadata by default
are output to an in-memory HSQL DB which can be overridden with the `spring.datasource.xxx` properties. Example yaml:

```
app:
  datasource:
    dialect: org.hibernate.dialect.MySQLDialect
    driver-class-name: org.mariadb.jdbc.Driver
    url: "jdbc:mysql://localhost:3306/stacke"
    username: "root"
    password: "password"
```

Streamlined ways to import are a W.I.P. For now, manual configuration of application.yaml is required, and running from
source is the simplest way to pass in custom datafiles. Once properties are configured, you can run locally with the
following:

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
