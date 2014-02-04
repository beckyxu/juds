JUDS
====

This is a fork of the juds project at [juds](https://github.com/mcfunley/juds "juds").

This fork exists to provide a usable juds artefact for publication to and
distribution from the Maven Central repository.

The original package namespace of `com.etsy.net` has been preserved, the maven
artefact should simply be a drop-in replacement.

The project from which this was forked provides no git tags from which to 
conclusively rebuild the last known juds release (i.e. version 0.94), so this
fork will simply be based off the latest master revision and specify artefact
version 0.94.1 instead.

The artefact dependency is:

```
<dependency>
    <groupId>uk.co.caprica</groupId>
    <artifactId>juds</artifactId>
    <version>0.94.1</version>
</dependency>
```
This artefact contains the native libraries for *only* Linux platforms (both
32-bit and 64-bit Linux). There are *no* other native libraries provided for
other platforms.

Releasing to Maven Central
==========================

Use 32-bit Linux.

Clone the git repository.

Edit `configure.ac` to update the package version and the package maintainer
e-mail address.

Update the pom.xml with the new version. Note that the pom is *not* used to
build the project, it is only used for publishing the artefact to the
repository.

Configure and build the project:

```
autoconf
./configure
make
```

If this works, a jar file will be created containing the native libraries for
both 32-bit and 64-bit Linux.

A requirement of publishing to Maven Central is that javadoc and sources jars
are also provided. The `Makefile` has been changed to build these artefacts.

Sign each of the artefacts that will be published (i.e. the pom, the jar, the
sources jar and the javadoc jar):

```
gpg --sign --detach-sign -a <jarfile>
gpg --sign --detach-sign -a pom.xml
```

This will produce `.asc` files with the digital signature.

Create the staging bundle:

```
jar -cvf bundle.jar juds-0.94.1.jar juds-0.94.1.jar.asc juds-0.94.1-sources.jar juds-0.94.1-sources.jar.asc juds-0.94.1-javadoc.jar juds-0.94.1-javadoc.jar.asc pom.xml pom.xml.asc
```

Upload the `bundle.jar` via Sonatype.

Tag the github repository.
