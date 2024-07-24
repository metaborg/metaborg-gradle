# Metaborg Gradle Plugins
[![Build][github-badge:build]][github:build]
[![License][license-badge]][license]
[![GitHub Release][github-badge:release]][github:release]
[![Documentation][documentation-badge]][documentation]

The Metaborg Gradle convention and development plugins, and the Metaborg dependency management and Gradle platform.

[![Documentation][documentation-button]][documentation]

| Gradle Plugin                           | Latest Release                                                                     | Latest Snapshot                                                                      |
|-----------------------------------------|------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| `org.metaborg.convention.settings`      | [![Release][mvn-rel-badge:convention.settings]][mvn:convention.settings]           | [![Snapshot][mvn-snap-badge:convention.settings]][mvn:convention.settings]           |
| `org.metaborg.convention.java`          | [![Release][mvn-rel-badge:convention.java]][mvn:convention.java]                   | [![Snapshot][mvn-snap-badge:convention.java]][mvn:convention.java]                   |
| `org.metaborg.convention.maven-publish` | [![Release][mvn-rel-badge:convention.maven-publish]][mvn:convention.maven-publish] | [![Snapshot][mvn-snap-badge:convention.maven-publish]][mvn:convention.maven-publish] |
| `org.metaborg.convention.root-project`  | [![Release][mvn-rel-badge:convention.root-project]][mvn:convention.root-project]   | [![Snapshot][mvn-snap-badge:convention.root-project]][mvn:convention.root-project]   |

| Artifact                         | Latest Release                                                       | Latest Snapshot                                                        |
|----------------------------------|----------------------------------------------------------------------|------------------------------------------------------------------------|
| `org.metaborg:catalog`           | [![Release][mvn-rel-badge:catalog]][mvn:catalog]                     | [![Snapshot][mvn-snap-badge:catalog]][mvn:catalog]                     |
| `org.metaborg:platform`          | [![Release][mvn-rel-badge:platform]][mvn:platform]                   | [![Snapshot][mvn-snap-badge:platform]][mvn:platform]                   |
| `org.metaborg:platform-latest`   | [![Release][mvn-rel-badge:platform-latest]][mvn:platform-latest]     | [![Snapshot][mvn-snap-badge:platform-latest]][mvn:platform-latest]     |
| `org.metaborg:platform-snapshot` | [![Release][mvn-rel-badge:platform-snapshot]][mvn:platform-snapshot] | [![Snapshot][mvn-snap-badge:platform-snapshot]][mvn:platform-snapshot] |
 

## Gradle Convention
The `org.metaborg.convention` plugins applies any conventional configuration to Metaborg build and projects. It has the following plugins:

- `org.metaborg.convention.settings`: Configures a build (in `settings.gradle.kts`) by applying a version catalog and the Develocity plugin.
- `org.metaborg.convention.java`: Configures a project as a Java project (library or application).
- `org.metaborg.convention.maven-publish`: Configures the Maven publications for a project.
- `org.metaborg.convention.root-project`: Configures the root project of a Gradle multi-project build.


## Gradle Dependency Management
The `org.metaborg:catalog` artifact provides recommended versions for dependencies, and should be used in projects that are part of Spoofax.

The `org.metaborg:platform` artifact enforces particular versions for Spoofax dependencies, and should be used by consumers of Spoofax libraries.

For special use cases, the `org.metaborg:platform-latest` and `org.metaborg:platform-snapshot` artifacts provide any latest releases and snapshots of Spoofax dependencies, respectively. These may not have been tested together. Therefore, it is recommended to use a particular release of `org.metaborg:platform` in production instead.


## License
Copyright 2024 [Programming Languages Group](https://pl.ewi.tudelft.nl/), [Delft University of Technology](https://www.tudelft.nl/)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an **"as is" basis, without warranties or conditions of any kind**, either express or implied. See the License for the specific language governing permissions and limitations under the License.



[github-badge:build]: https://img.shields.io/github/actions/workflow/status/metaborg/metaborg-gradle/build.yaml
[github:build]: https://github.com/metaborg/metaborg-gradle/actions
[license-badge]: https://img.shields.io/github/license/metaborg/metaborg-gradle
[license]: https://github.com/metaborg/metaborg-gradle/blob/main/LICENSE
[github-badge:release]: https://img.shields.io/github/v/release/metaborg/metaborg-gradle?display_name=release
[github:release]: https://github.com/metaborg/metaborg-gradle/releases
[documentation-badge]: https://img.shields.io/badge/docs-latest-brightgreen
[documentation]: https://spoofax.dev/metaborg-gradle/
[documentation-button]: https://img.shields.io/badge/Documentation-blue?style=for-the-badge&logo=googledocs&logoColor=white

[mvn:convention.settings]:                  https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.settings~org.metaborg.convention.settings.gradle.plugin~~~
[mvn:convention.java]:                      https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.java~org.metaborg.convention.java.gradle.plugin~~~
[mvn:convention.maven-publish]:             https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.maven-publish~org.metaborg.convention.maven-publish.gradle.plugin~~~
[mvn:convention.root-project]:              https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.root-project~org.metaborg.convention.root-project.gradle.plugin~~~
[mvn:catalog]:                              https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg~catalog~~~
[mvn:platform]:                             https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg~platform~~~
[mvn:platform-latest]:                      https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg~platform-latest~~~
[mvn:platform-snapshot]:                    https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg~platform-snapshot~~~

[mvn-rel-badge:convention.settings]:        https://img.shields.io/nexus/r/org.metaborg.convention.settings/org.metaborg.convention.settings.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:convention.java]:            https://img.shields.io/nexus/r/org.metaborg.convention.java/org.metaborg.convention.java.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:convention.maven-publish]:   https://img.shields.io/nexus/r/org.metaborg.convention.maven-publish/org.metaborg.convention.maven-publish.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:convention.root-project]:    https://img.shields.io/nexus/r/org.metaborg.convention.root-project/org.metaborg.convention.root-project.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:catalog]:                    https://img.shields.io/nexus/r/org.metaborg/catalog?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:platform]:                   https://img.shields.io/nexus/r/org.metaborg/platform?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:platform-latest]:            https://img.shields.io/nexus/r/org.metaborg/platform-latest?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-rel-badge:platform-snapshot]:          https://img.shields.io/nexus/r/org.metaborg/platform-snapshot?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20

[mvn-snap-badge:convention.settings]:       https://img.shields.io/nexus/s/org.metaborg.convention.settings/org.metaborg.convention.settings.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:convention.java]:           https://img.shields.io/nexus/s/org.metaborg.convention.java/org.metaborg.convention.java.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:convention.maven-publish]:  https://img.shields.io/nexus/s/org.metaborg.convention.maven-publish/org.metaborg.convention.maven-publish.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:convention.root-project]:   https://img.shields.io/nexus/s/org.metaborg.convention.root-project/org.metaborg.convention.root-project.gradle.plugin?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:catalog]:                   https://img.shields.io/nexus/s/org.metaborg/catalog?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:platform]:                  https://img.shields.io/nexus/s/org.metaborg/platform?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:platform-latest]:           https://img.shields.io/nexus/s/org.metaborg/platform-latest?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
[mvn-snap-badge:platform-snapshot]:         https://img.shields.io/nexus/s/org.metaborg/platform-snapshot?server=https%3A%2F%2Fartifacts.metaborg.org&label=%20
