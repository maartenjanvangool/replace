# Replace

A small CLI app for me to experiment with GraalVM, Micronaut and PicoCli. It does find and replace. 

## Installation
```bash
curl ""
sudo mv replace /usr/bin/replace

```
## usage

String replacement: 
```bash
replace -t "blablabla" "bla" "la" 
```

Replace stuff from a file:
```bash
replace -f "bla.txt" "bla" "la" > la.txt 
```

## Build native package

```bash
mvn clean package
native-image -jar target/replace-0.1.jar
```
