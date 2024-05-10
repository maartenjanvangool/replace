# Replace

A small CLI app for me to experiment with GraalVM, Micronaut and PicoCli. It does find and replace. 

## Installation
```bash
curl -L -O https://github.com/maartenjanvangool/replace/releases/download/0.1/replace
chmod +x replace
sudo mv replace /usr/bin/replace
```

## Usage
String replacement: 
```bash
replace "blablabla" "bla" "la"

lalala 
```

replace first:
```bash
replace "blablabla" "bla" "la" --first
lablabla 
```

replace from an index:
```bash
replace "blablabla" "bla" "la" 2
blalala
```

Replace stuff from a file:
```bash
replace -f "bla.txt" "bla" "la"
```

## Build native executable

```bash
./mvnw package -Dpackaging=native-image
```
