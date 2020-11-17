package StSShapeShifter;

import StSShapeShifter.cards.*;
import StSShapeShifter.cards.tempCards.*;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.events.IdentityCrisisEvent;
import StSShapeShifter.potions.AnimalAmalgamationPotion;
import StSShapeShifter.relics.*;
import StSShapeShifter.util.IDCheckDontTouchPls;
import StSShapeShifter.util.TextureLoader;
import StSShapeShifter.variables.DefaultCustomVariable;
import StSShapeShifter.variables.DefaultSecondMagicNumber;
import StSShapeShifter.variables.GrowVariable;
import StSShapeShifter.variables.WitherVariable;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
// Please don't just mass replace "theDefault" with "yourMod" everywhere.
// It'll be a bigger pain for you. You only need to replace it in 3 places.
// I comment those places below, under the place where you set your ID.

//TODO: FIRST THINGS FIRST: RENAME YOUR PACKAGE AND ID NAMES FIRST-THING!!!
// Right click the package (Open the project pane on the left. Folder with black dot on it. The name's at the very top) -> Refactor -> Rename, and name it whatever you wanna call your mod.
// Scroll down in this file. Change the ID from "theDefault:" to "yourModName:" or whatever your heart desires (don't use spaces). Dw, you'll see it.
// In the JSON strings (resources>localization>eng>[all them files] make sure they all go "yourModName:" rather than "theDefault". You can ctrl+R to replace in 1 file, or ctrl+shift+r to mass replace in specific files/directories (Be careful.).
// Start with the DefaultCommon cards - they are the most commented cards since I don't feel it's necessary to put identical comments on every card.
// After you sorta get the hang of how to make cards, check out the card template which will make your life easier

/*
 * With that out of the way:
 * Welcome to this super over-commented Slay the Spire modding base.
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (character,
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, couple of relics, etc.
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. MIT licence applies. Happy modding!
 *
 * And pls. Read the comments.
 */

@SpireInitializer
public class ShapeshifterMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(ShapeshifterMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Shapeshifter Mod";
    private static final String AUTHOR = "Austin Gregory";
    private static final String DESCRIPTION = "The Shapeshifter Mod";
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);
    //public static final Color SHAPESHIFTER_BROWN = CardHelper.getColor(160.0f, 82.0f, 45.0f);
    public static final Color SHAPESHIFTER_BROWN = new Color(160.0f, 82.0f, 45.0f, 1.0f);
    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown
    
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_GRAY = "StSShapeShifterResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "StSShapeShifterResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "StSShapeShifterResources/images/512/bg_power_default_gray.png";
    
    private static final String ENERGY_ORB_DEFAULT_GRAY = "StSShapeShifterResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "StSShapeShifterResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "StSShapeShifterResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "StSShapeShifterResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "StSShapeShifterResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "StSShapeShifterResources/images/1024/card_default_gray_orb.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "StSShapeShifterResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_SHAPESHIFTER_BUTTON = "StSShapeShifterResources/images/charSelect/shapeshifter_button4.png";
    private static final String THE_DEFAULT_PORTRAIT = "StSShapeShifterResources/images/charSelect/DefaultCharacterPortraitBG.png";
    private static final String THE_SHAPESHIFTER_PORTRAIT = "StSShapeShifterResources/images/charSelect/shapeshifter_portrait_bg2.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "StSShapeShifterResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "StSShapeShifterResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "StSShapeShifterResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "StSShapeShifterResources/images/Badge.png";
    
    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "StSShapeShifterResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "StSShapeShifterResources/images/char/defaultCharacter/skeleton.json";
    public static final String SHAPESHIFTER_SKELETON_ATLAS = "StSShapeShifterResources/images/ShapeShifter/ShapeShifterCharacter.atlas";
    public static final String SHAPESHIFTER_SKELETON_JSON = "StSShapeShifterResources/images/ShapeShifter/ShapeShifterCharacter.json";


    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/images/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================
    
    public ShapeshifterMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
      /*
           (   ( /(  (     ( /( (            (  `   ( /( )\ )    )\ ))\ )
           )\  )\()) )\    )\()))\ )   (     )\))(  )\()|()/(   (()/(()/(
         (((_)((_)((((_)( ((_)\(()/(   )\   ((_)()\((_)\ /(_))   /(_))(_))
         )\___ _((_)\ _ )\ _((_)/(_))_((_)  (_()((_) ((_|_))_  _(_))(_))_
        ((/ __| || (_)_\(_) \| |/ __| __| |  \/  |/ _ \|   \  |_ _||   (_)
         | (__| __ |/ _ \ | .` | (_ | _|  | |\/| | (_) | |) |  | | | |) |
          \___|_||_/_/ \_\|_|\_|\___|___| |_|  |_|\___/|___/  |___||___(_)
      */
      
        setModID("StSShapeShifter");
        // cool
        // TODO: NOW READ THIS!!!!!!!!!!!!!!!:
        
        // 1. Go to your resources folder in the project panel, and refactor> rename ShapeShifterResources to
        // yourModIDResources.
        
        // 2. Click on the localization > eng folder and press ctrl+shift+r, then select "Directory" (rather than in Project)
        // replace all instances of theDefault with yourModID.
        // Because your mod ID isn't the default. Your cards (and everything else) should have Your mod id. Not mine.
        
        // 3. FINALLY and most importantly: Scroll up a bit. You may have noticed the image locations above don't use getModID()
        // Change their locations to reflect your actual ID rather than theDefault. They get loaded before getID is a thing.
        
        logger.info("Done subscribing");
        
        /*logger.info("Creating the color " + TheDefault.Enums.COLOR_GRAY.toString());
        
        BaseMod.addColor(TheDefault.Enums.COLOR_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY, DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");*/

        logger.info("Creating the color " + ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR.toString());

        BaseMod.addColor(
                ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR,
                SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN, SHAPESHIFTER_BROWN,
                "StSShapeShifterResources/images/512/card_bg_ss_512.png",
                "StSShapeShifterResources/images/512/card_bg_ss_512.png",
                "StSShapeShifterResources/images/512/card_bg_ss_512.png",
                ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT,
                SKILL_DEFAULT_GRAY_PORTRAIT,
                POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT,
                CARD_ENERGY_ORB);

        logger.info("Done creating the color");
        
        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = ShapeshifterMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = ShapeshifterMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = ShapeshifterMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    public static void initialize() {
        logger.info("========================= Initializing Shapeshifter Mod =========================");
        ShapeshifterMod shapeshiftermod = new ShapeshifterMod();
        logger.info("========================= /Shapeshifter Mod Initialized/ =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + ShapeShifter.Enums.SHAPESHIFTER.toString());

        BaseMod.addCharacter(new ShapeShifter("TheShapeShifter", ShapeShifter.Enums.SHAPESHIFTER),
                THE_SHAPESHIFTER_BUTTON,
                THE_SHAPESHIFTER_PORTRAIT,
                ShapeShifter.Enums.SHAPESHIFTER);
        
        receiveEditPotions();
        logger.info("Added " + ShapeShifter.Enums.SHAPESHIFTER.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:
            
            enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        //BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================
    
    
    // ================ ADD POTIONS ===================
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheDefault.Enums.THE_DEFAULT);
        BaseMod.addPotion(AnimalAmalgamationPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, AnimalAmalgamationPotion.POTION_ID, ShapeShifter.Enums.SHAPESHIFTER);

        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================
    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // Take a look at https://github.com/daviscook477/BaseMod/wiki/AutoAdd
        // as well as
        // https://github.com/kiooeht/Bard/blob/e023c4089cc347c60331c78c6415f489d19b6eb9/src/main/java/com/evacipated/cardcrawl/mod/bard/BardMod.java#L319
        // for reference as to how to turn this into an "Auto-Add" rather than having to list every relic individually.
        // Of note is that the bard mod uses it's own custom relic class (not dissimilar to our AbstractDefaultCard class for cards) that adds the 'color' field,
        // in order to automatically differentiate which pool to add the relic too.

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new AnimalHeart(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new TheShapeShiftersMagnolia(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new AlarmClock(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new AlchemistsNecklace(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new BeastkeepersGift(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new DruidicMedallion(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new EmeraldPantherFigurine(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new FruitTree(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new LemurHeaddress(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new ThornsOfSummi(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new WristwrapsOfDiscovery(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new Fertilizer(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new FrogOfVitality(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);
        BaseMod.addRelicToCustomPool(new LoyalLobster(), ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR);

        
        // This adds a relic to the Shared pool. Every character can find this relic.
        //BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);
        
        // Mark relics as seen (the others are all starters so they're marked as seen in the character file
        //UnlockTracker.markRelicAsSeen(BottledPlaceholderRelic.ID);
        logger.info("Done adding relics!");
    }
    
    // ================ /ADD RELICS/ ===================
    
    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variables");
        // Add the Custom Dynamic variables
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        BaseMod.addDynamicVariable(new GrowVariable());
        BaseMod.addDynamicVariable(new WitherVariable());

        
        logger.info("Adding cards");
        // Add the cards
        // Don't delete these default cards yet. You need 1 of each type and rarity (technically) for your game not to crash
        // when generating card rewards/shop screen items.
        // This method automatically adds any cards inside the cards package, found under yourModName.cards.
        // For more specific info, including how to exclude classes from being added:
        // https://github.com/daviscook477/BaseMod/wiki/AutoAdd

        // The ID for this function isn't actually your modid as used for prefixes/by the getModID() method.
        // It's the mod id you give MTS in ModTheSpire.json - by default your artifact ID in your pom.xml
        BaseMod.addCard(new ShapeShifter_Strike());
        BaseMod.addCard(new ShapeShifter_Defend());
        BaseMod.addCard(new Efflorescence());
        BaseMod.addCard(new AdaptiveStrikes());
        BaseMod.addCard(new Uproot_Redwood());
        BaseMod.addCard(new FlowerShield());
        BaseMod.addCard(new Mystical_Branch());
        BaseMod.addCard(new Summon_Mountains());
        BaseMod.addCard(new Sap_Burst());
        BaseMod.addCard(new Tortoise_Form());
        BaseMod.addCard(new Lynx_Form());
        BaseMod.addCard(new Rest());
        BaseMod.addCard(new Maul());
        BaseMod.addCard(new FesteringBite());
        BaseMod.addCard(new Guard());
        BaseMod.addCard(new BloomingSlash());
        BaseMod.addCard(new BurstingSpore());
        BaseMod.addCard(new ThornToss());
        BaseMod.addCard(new VineHandcuffs());
        BaseMod.addCard(new BarkSkin());
        BaseMod.addCard(new FallingLeaves());
        BaseMod.addCard(new PoisonIvy());
        BaseMod.addCard(new DeerForm());
        BaseMod.addCard(new BearForm());
        BaseMod.addCard(new ShiftingStrike());
        BaseMod.addCard(new Sapling());
        BaseMod.addCard(new DiverseSpirit());
        BaseMod.addCard(new GoldenBerries());
        BaseMod.addCard(new Hunt());
        BaseMod.addCard(new Bonk());
        BaseMod.addCard(new MysticalSeed());
        BaseMod.addCard(new ThrashAbout());
        BaseMod.addCard(new DragonForm());
        BaseMod.addCard(new HydraForm());
        BaseMod.addCard(new LeapingSlash());
        BaseMod.addCard(new DeepWound());
        BaseMod.addCard(new OpportuneSwoop());
        BaseMod.addCard(new ThornedSkin());
        BaseMod.addCard(new CursedApple());
        BaseMod.addCard(new MultiSpiritedStrike());
        BaseMod.addCard(new SkullBash());
        BaseMod.addCard(new MammothForm());
        //BaseMod.addCard(new Blossom());
        BaseMod.addCard(new Blossom2());
        BaseMod.addCard(new RestoreBalance());
        // BaseMod.addCard(new Absorb());
        //BaseMod.addCard(new Wildfire());
        BaseMod.addCard(new Wildfire2());
        BaseMod.addCard(new ForestFire());
        BaseMod.addCard(new MagicMushrooms());
        BaseMod.addCard(new Consume());
        BaseMod.addCard(new TopazSkin());
        BaseMod.addCard(new RatForm());
        BaseMod.addCard(new PorcupineForm());
        BaseMod.addCard(new EntForm());
        BaseMod.addCard(new Flourish());
        BaseMod.addCard(new SpiritOfTheEarth());
        BaseMod.addCard(new PhoenixForm());
        BaseMod.addCard(new Tornado());
        BaseMod.addCard(new IntimidatingAura());
        BaseMod.addCard(new EagleForm());
        BaseMod.addCard(new OakBlade());
        BaseMod.addCard(new Cherry());
        BaseMod.addCard(new Acorn());
        BaseMod.addCard(new Dragonfruit());
        BaseMod.addCard(new Pineapple());
        BaseMod.addCard(new Banana());
        BaseMod.addCard(new FruitBasket());
        BaseMod.addCard(new Avocado());
        BaseMod.addCard(new ReplenishingNectar());
        BaseMod.addCard(new VileMushroom());
        BaseMod.addCard(new LionForm());
        BaseMod.addCard(new MonkeyForm());
        BaseMod.addCard(new SquirrelForm());
        BaseMod.addCard(new FoxForm());
        BaseMod.addCard(new RockSlide());
        BaseMod.addCard(new RitualOfTheElders());
        BaseMod.addCard(new RitualOfTheExtinct());
        BaseMod.addCard(new Pounce());
        BaseMod.addCard(new BurrBomb());
        BaseMod.addCard(new Earthquake());
        BaseMod.addCard(new WolfForm());
        BaseMod.addCard(new Roar());
        BaseMod.addCard(new WaspSwarmForm());
        BaseMod.addCard(new OwlForm());
        BaseMod.addCard(new HummingbirdForm());
        BaseMod.addCard(new FullMoon());
        BaseMod.addCard(new PowerShifter());
        BaseMod.addCard(new Ensconce());
        BaseMod.addCard(new Spring());
        BaseMod.addCard(new Summer());
        BaseMod.addCard(new Autumn());
        BaseMod.addCard(new Winter());
        BaseMod.addCard(new BulwarkOfDiscovery());
        BaseMod.addCard(new MooseForm());
        BaseMod.addCard(new Evolution());
        BaseMod.addCard(new HippoForm());
        BaseMod.addCard(new RaptorForm());
        BaseMod.addCard(new FlamingoForm());
        BaseMod.addCard(new RhinoCalfForm());
        //BaseMod.addCard(new FertileSoil());
        BaseMod.addCard(new Flood());
        BaseMod.addCard(new WiltingWrath());
        BaseMod.addCard(new BaskInBloom());
        //BaseMod.addCard(new Harmony());
        BaseMod.addCard(new BladeOfGrass());
        BaseMod.addCard(new SunflowerShuriken());
        BaseMod.addCard(new ClimbTree());
        BaseMod.addCard(new PantherForm());
        BaseMod.addCard(new Rabid());
        BaseMod.addCard(new Garden());
        BaseMod.addCard(new EmbraceTheCycle());
        //BaseMod.addCard(new BurstOfLife());
        //BaseMod.addCard(new EscapeDeath());
        BaseMod.addCard(new Vengeance());
        BaseMod.addCard(new Forgiveness());
        BaseMod.addCard(new BurstOfLifeEscapeDeath());
        BaseMod.addCard(new CosmicShifter());
        BaseMod.addCard(new Synchronicity());
        BaseMod.addCard(new WolfsBane());



        new AutoAdd(getModID())
            .packageFilter("StSShapeShifter.cards")
            .setDefaultSeen(true)
            .cards();

        // .setDefaultSeen(true) unlocks the cards
        // This is so that they are all "seen" in the library,
        // for people who like to look at the card list before playing your mod

        logger.info("Done adding cards!");
    }
    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Card-Strings.json");
        
        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Power-Strings.json");
        
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Relic-Strings.json");
        
        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Event-Strings.json");
        
        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Potion-Strings.json");
        
        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Character-Strings.json");
        
        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Orb-Strings.json");

        // OrbStrings
        BaseMod.loadCustomStringsFile(StanceStrings.class,
                getModID() + "Resources/localization/eng/DefaultMod-Stance-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    // ================ /LOAD THE TEXT/ ===================
    
    // ================ LOAD THE KEYWORDS ===================
    
    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/DefaultMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        BaseMod.addKeyword(new String[]{"grow"}, "Whenever you play this card, it is improved for the rest of combat");
        BaseMod.addKeyword(new String[]{"wither"}, "Whenever you play this card, it is worsened for the rest of combat");
        BaseMod.addKeyword(new String[]{"form"}, "You can only occupy one Form at a time. Forms perform some effect upon entrance, and have a persisting effect while you remain in that Form.");
        BaseMod.addKeyword(new String[]{"dodge"}, "Dodge the next source of damage.");
        BaseMod.addKeyword(new String[]{"discover"}, "Choose between 3 cards to temporarily add to your deck.");
        BaseMod.addKeyword(new String[]{"blooming"}, "If your Bloom Count is greater than or equal to 10, you are Blooming.");
        BaseMod.addKeyword(new String[]{"blossoming"}, "If your Bloom Count is greater than or equal to 20, you are Blossoming.");
        BaseMod.addKeyword(new String[]{"wilting"}, "If your Bloom Count is less than or equal to -10, you are Wilting.");
        BaseMod.addKeyword(new String[]{"decaying"}, "If your Bloom Count is less than or equal to -20, you are Decaying.");
        BaseMod.addKeyword(new String[]{"balanced"}, "If your Bloom Count is less than or equal to 3 AND greater than or equal to -3, you are Balanced.");
        BaseMod.addKeyword(new String[]{"bloom count"}, "Bloom Count is the positive number associated with the Shapeshifter's Magnolia.");
        BaseMod.addKeyword(new String[]{"wilt count"}, "Wilt Count is the negative number associate with the Shapeshifter's Magnolia.");
        /* (keywords != null) {
            for (Keyword keyword : keywords) {
                //BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }*/
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    


    public static String imgFromId(String id) {
        String unprefixedId = id.replace(ShapeshifterMod.getModID() + ":","");
        return ShapeshifterMod.makeCardPath(String.format("card-art-generated/%1$s.png", unprefixedId));
    }

    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
