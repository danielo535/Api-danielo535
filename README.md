<div align="center">

  # Api-danielo535

  [![](https://jitpack.io/v/danielo535/Api-danielo535.svg)](https://jitpack.io/#danielo535/Api-danielo535)
  ![](https://img.shields.io/github/v/release/danielo535/Api-danielo535.svg)
  ![](https://img.shields.io/github/last-commit/danielo535/Api-danielo535.svg)<br />

</div>

## Info Api

Api-danielo535 has been created for individuals developing Minecraft plugins, enabling interaction with various plugins such as FastAsyncWorldEdit, WorldGuard, and DecentHolograms.

## Features
### FastAsyncWorldEdit Integration
- **Pasting Schematics**: Allows pasting schematics at specified coordinates using FastAsyncWorldEdit..
### WorldGuard Events
- **Entry and Exit Events from Regions**: The API includes events related to WorldGuard, which enable monitoring and responding to player entry and exit from regions.
### WorldGuard Integration
- **Region Membership Checking**: The API enables checking whether a specific player, block, or entity is located within a specified WorldGuard region.
### DecentHolograms Integration
- **Rich Functionality**: The API facilitates easy creation of holograms using DecentHolograms.
### Hex-Format Message Coloring
- **Message Coloring**: Adds support for coloring messages using color codes in hex format (#RRGGBB)..

## Installation
#### Info: replace `TAG` with latest [releases](https://github.com/danielo535/Api-danielo535/releases/)

### Maven

#### Repository
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
#### Dependency
```xml
<dependency>
    <groupId>com.github.danielo535</groupId>
    <artifactId>Api-danielo535</artifactId>
    <version>TAG</version>
</dependency>
```

### Gradle

#### Repository

Groovy:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

Kotlin:
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}
```
#### Dependency

Groovy/Kotlin:
```groovy
dependencies {
        implementation 'com.github.danielo535:Api-danielo535:TAG'
}
```

## Usage

### Pasting Schematic
****
Example:
```java
File directory = new File("plugins/FastAsyncWorldEdit/schematics/");
Location location = new Location(getServer().getWorld("world"),0,0,0);
SchematicManager.pasteSchematic("NameSchematic",location,directory);
```

### WorldGuard Events
****
Example:
```java
@Override
public void onEnable() {
    getServer().getPluginManager().registerEvents(new PlayerRegionListener(), this);
}

@EventHandler
public void onEnterRegion(PlayerJoinRegionEvent event) {
    Player player = event.getPlayer();
    String region = event.getRegionName();
    // Add custom region entry handling logic
}

@EventHandler
public void onExitRegion(PlayerQuitRegionEvent event) {
    Player player = event.getPlayer();
    String region = event.getRegionName();
    // Add custom region entry handling logic
}
```

### Region Checking
****
Example:
```java
getPlayerRegions(player) //Get a list of regions containing the player
getBlockRegions(block) //Get a list of regions containing the block
getEntityRegions(entity) //Get a list of regions containing the entity 

isPlayerInRegion(player,"region") //Check if a player is in a region
isBlockInRegion(block,"region") //Check if a block is in a region
isEntityInRegion(entity,"region") //Check if an entity is in a region

isPlayerInRegionList(player, Arrays.asList("region1","region2")) //Check if a player is in one of several regions
isBlockInRegionList(block, Arrays.asList("region1","region2")) //Check if a block is in one of several regions
isEntityInRegionList(entity, Arrays.asList("region1","region2")) //Check if an entity is in one of several regions
```

### Region Checking
****
Example:
```java
/**
 * Updates the content of an existing hologram.
 *
 * @param hologramName The name of the hologram to update.
 * @param lines        A list of strings representing the new lines of text for the hologram.
 */
updateHologram("HologramName",Arrays.asList("HologramLine 1","HologramLine 2"));

/**
 * Creates a new hologram at a specified location with the given content.
 *
 * @param location    The location where the hologram should be created.
 * @param hologramName The name to assign to the new hologram.
 * @param lines       A list of strings representing the lines of text for the hologram.
 * @param saveFile    Determines whether to save the hologram to a file for persistence.
 */
createHologram(location,"HologramName",Arrays.asList("HologramLine 1","HologramLine 2"),false);

/**
 * Removes an existing hologram.
 *
 * @param hologramName The name of the hologram to remove.
 */
removeHologram("HologramName");
```

### Message Coloring
****
Example:
```java
/**
 * Applies color to a text string with hexadecimal color codes.
 *
 * @param message The text message to colorize.
 * @return The colorized text message.
 */
colorText("&#FF9900Messages");

/**
 * Applies color to a list of text strings with hexadecimal color codes.
 *
 * @param messages The list of text messages to colorize.
 * @return The list of colorized text messages.
 */
colorList(Arrays.asList("&#FF9900Messages1","&#FF9900Messages2"));
```
