This is a [giter8](https://github.com/n8han/giter8) template for removing
the boilerplate in setting up a new SBT multi-project definition:

* SBT 0.11.2 and Scala 2.9.1
* Default "all" aggregate project and a "core" sub project
* `main` and `test` source directories and stubs
* [Cross-builds](https://github.com/harrah/xsbt/wiki/Cross-Build) for Scala 2.8.1, 2.9.0, 2.9.0-1
* [specs2](http://etorreborre.github.com/specs2/) gymnastics for the right versions under cross-compilation
* project `name`, `organization` and `version` customizable as variables
* everything in your project's base package imported automatically in repl sessions
