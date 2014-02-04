rm *.asc bundle.jar
gpg --sign --detach-sign -a pom.xml
gpg --sign --detach-sign -a juds-0.94.1.jar
gpg --sign --detach-sign -a juds-0.94.1-javadoc.jar
gpg --sign --detach-sign -a juds-0.94.1-sources.jar
jar cvf bundle.jar pom.xml *.jar *.asc
