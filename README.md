# Metaborg Gradle Plugins
[![Build][github-build-badge]][github-build]
[![License][license-badge]][license]
[![GitHub Release][github-release-badge]][github-release]
[![Documentation][documentation-badge]][documentation]

The Metaborg Gradle convention and development plugins.

[![Documentation][documentation-button]][documentation]

| Artifact                                | Latest Release                                                                                                   |
|-----------------------------------------|------------------------------------------------------------------------------------------------------------------|
| `org.metaborg.convention.settings`      | [![org.metaborg.convention.settings][maven-badge:convention.settings]][maven:convention.settings]                |
| `org.metaborg.convention.java`          | [![org.metaborg.convention.java][maven-badge:convention.java]][maven:convention.java]                            |
| `org.metaborg.convention.maven-publish` | [![org.metaborg.convention.maven-publish][maven-badge:convention.maven-publish]][maven:convention.maven-publish] |
| `org.metaborg.convention.root-project`  | [![org.metaborg.convention.root-project][maven-badge:convention.root-project]][maven:convention.root-project]    |

 

## Gradle Convention
The `org.metaborg.convention` plugins applies any conventional configuration to Metaborg build and projects. It has the following plugins:

- `org.metaborg.convention.settings`: Configures a build (in `settings.gradle.kts`) by applying a version catalog and the Develocity plugin.
- `org.metaborg.convention.java`: Configures a project as a Java project (library or application).
- `org.metaborg.convention.maven-publish`: Configures the Maven publications for a project.
- `org.metaborg.convention.root-project`: Configures the root project of a Gradle multi-project build.



## License
Copyright 2024 Delft University of Technology

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at <https://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an **"as is" basis, without warranties or conditions of any kind**, either express or implied. See the License for the specific language governing permissions and limitations under the License.



[github-build-badge]: 
https://img.shields.io/github/actions/workflow/status/metaborg/metaborg-gradle/build.yaml
[github-build]: https://github.com/metaborg/metaborg-gradle/actions
[license-badge]: https://img.shields.io/github/license/metaborg/metaborg-gradle
[license]: https://github.com/metaborg/metaborg-gradle/blob/main/LICENSE
[github-release-badge]: https://img.shields.io/github/v/release/metaborg/metaborg-gradle
[github-release]: https://github.com/metaborg/metaborg-gradle/releases
[documentation-badge]: https://img.shields.io/badge/docs-latest-brightgreen
[documentation]: https://spoofax.dev/metaborg-gradle/
[documentation-button]: https://img.shields.io/badge/Documentation-blue?style=for-the-badge&logo=googledocs&logoColor=white


|-----------------------------------------|------------------------------------------------------------------------------------------------------------------|
| `org.metaborg.convention.settings`      | [![org.metaborg.convention.settings][maven-badge:convention.settings]][maven:convention.settings]                      |
| `org.metaborg.convention.java`          | [![org.metaborg.convention.java][maven-badge:convention.java]][maven:convention.java]                            |
| `org.metaborg.convention.maven-publish` | [![org.metaborg.convention.maven-publish][maven-badge:convention.maven-publish]][maven:convention.maven-publish] |
| `org.metaborg.convention.root-project`  | [![org.metaborg.convention.root-project][maven-badge:convention.root-project]][maven:convention.root-project]    |

[maven-badge:convention.settings]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fartifacts.metaborg.org%2Fcontent%2Frepositories%2Freleases%2Forg%2Fmetaborg%2Fconvention%2Fsettings%2Forg.metaborg.convention.settings.gradle.plugin%2Fmaven-metadata.xml
[maven-badge:convention.java]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fartifacts.metaborg.org%2Fcontent%2Frepositories%2Freleases%2Forg%2Fmetaborg%2Fconvention%2Fjava%2Forg.metaborg.convention.java.gradle.plugin%2Fmaven-metadata.xml
[maven-badge:convention.maven-publish]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fartifacts.metaborg.org%2Fcontent%2Frepositories%2Freleases%2Forg%2Fmetaborg%2Fconvention%2Fmaven-publish%2Forg.metaborg.convention.maven-publish.gradle.plugin%2Fmaven-metadata.xml
[maven-badge:convention.root-project]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fartifacts.metaborg.org%2Fcontent%2Frepositories%2Freleases%2Forg%2Fmetaborg%2Fconvention%2Froot-project%2Forg.metaborg.convention.root-project.gradle.plugin%2Fmaven-metadata.xml

[maven:convention.settings]: https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.settings~org.metaborg.convention.settings.gradle.plugin~~~
[maven:convention.java]: https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.java~org.metaborg.convention.java.gradle.plugin~~~
[maven:convention.maven-publish]: https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.maven-publish~org.metaborg.convention.maven-publish.gradle.plugin~~~
[maven:convention.root-project]: https://artifacts.metaborg.org/#nexus-search;gav~org.metaborg.convention.root-project~org.metaborg.convention.root-project.gradle.plugin~~~
