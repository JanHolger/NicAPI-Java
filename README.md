# NicAPI-Java
Java Client for the LumaServ NicAPI

## Example Usage
```java
NicAPI api = new NicAPI("YOUR-API-TOKEN");
for(Domain domain : api.domain().getDomains()){
  System.out.println(domain.name);
}
```
