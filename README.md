A Simple JSON libary for Java
=============================
This is my JSON library.  There are many like it but this one is mine.

Basic Use
---------

Creates Json with default parser and settings

Json()

Builds a JsonObject with a name field

Json().object().string("name", "JsonObject").make()

Creates a JsonObject from a json String

JsonObject jo = Json().toObject("{}")

Creates a user specific type from a json String

Empty empty = Json().toObject("{}", Empty.class)

Converts a JsonElement to Json

String json = Json().toJson(jo);

Converts a user specific type into Json

Address address = new Address("42 Wallaby Way", "Sydney", "AU");
String json = Json().toJson(address);

Advanced Use
------------

Creates Json with a super duper parser

Json json = Json(superDuperParser)

Builds a JsonObject with a name field using the superDuperParser

Json(superDuperParser).object().string("name", "JsonObject").make()

Creates a JsonObject from a json String using the superDuperParser

JsonObject jo = Json(superDuperParser).toObject("{}")

Creates a user specific type from a json String using the superDuperParser

Empty empty = Json(superDuperParser).toObject("{}", Empty.class)

Converts a JsonElement to a json String using the superDuperParser

String json = Json(superDuperParser).toString(jo);

Converts a user specific type into a json String using the superDuperParser

Address address = new Address("42 Wallaby Way", "Sydney", "AU");
String json = Json(superDuperParser).toString(address);

Think: Say what is True, Helpful, Important , Necessary, Kind

