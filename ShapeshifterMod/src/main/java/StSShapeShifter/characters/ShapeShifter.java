package StSShapeShifter.characters;

import StSShapeShifter.relics.*;
import StSShapeShifter.stances.PhoenixFormStance;
import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.*;

import java.awt.*;
import java.util.ArrayList;

import static StSShapeShifter.ShapeshifterMod.*;
import static StSShapeShifter.characters.ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

//Wiki-page https://github.com/daviscook477/BaseMod/wiki/Custom-Characters
//and https://github.com/daviscook477/BaseMod/wiki/Migrating-to-5.0
//All text (starting description and loadout, anything labeled TEXT[]) can be found in DefaultMod-character-Strings.json in the resources

public class ShapeShifter extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(ShapeshifterMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================
    // These are enums for your Characters color (both general color and for the card library) as well as
    // an enum for the name of the player class - IRONCLAD, THE_SILENT, DEFECT, YOUR_CLASS ...
    // These are all necessary for creating a character. If you want to find out where and how exactly they are used
    // in the basegame (for fun and education) Ctrl+click on the PlayerClass, CardColor and/or LibraryType below and go down the
    // Ctrl+click rabbit hole

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass SHAPESHIFTER;
        @SpireEnum(name = "SHAPESHIFTER_BROWN_COLOR") // These two HAVE to have the same absolutely identical name.
        public static AbstractCard.CardColor SHAPESHIFTER_CARD_COLOR;
        @SpireEnum(name = "SHAPESHIFTER_BROWN_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType SHAPESHIFTER_LIBRARY_COLOR;
    }


    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 78;
    public static final int MAX_HP = 78;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    //private static final String ID = makeID("ShapeShifterCharacter");
    //private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = {"The Shapeshifter"};
    private static final String[] TEXT = {"A wanderer of the forest who has discovered the lost art of shapeshifting.", "Through a combination of forms drawn from connecting with animals and powers drawn from communing with nature,", "the Shapeshifter seeks to rid the spire of its unnatural creatures."};

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer1.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer2.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer3.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer4.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer5.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer6.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer1d.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer2d.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer3d.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer4d.png",
            "StSShapeShifterResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public ShapeShifter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "StSShapeShifterResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "StSShapeShifterResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_DEFAULT_SHOULDER_2, // campfire pose
                THE_DEFAULT_SHOULDER_1, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================

        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(ShapeShifter_Strike.ID);
        retVal.add(ShapeShifter_Strike.ID);
        retVal.add(ShapeShifter_Strike.ID);
        retVal.add(ShapeShifter_Strike.ID);
        retVal.add(ShapeShifter_Strike.ID);

        retVal.add(ShapeShifter_Defend.ID);
        retVal.add(ShapeShifter_Defend.ID);
        retVal.add(ShapeShifter_Defend.ID);
        retVal.add(ShapeShifter_Defend.ID);
        retVal.add(ShapeShifter_Defend.ID);
/*        BaseMod.addCard(new FoxForm());
        BaseMod.addCard(new RockSlide());
        BaseMod.addCard(new RitualOfTheElders());
        BaseMod.addCard(new RitualOfTheExtinct());
        BaseMod.addCard(new Pounce());
        BaseMod.addCard(new BurrBomb());*/


        retVal.add(Tortoise_Form.ID);
        retVal.add(Lynx_Form.ID);
        //retVal.add(Ensconce.ID);
        //retVal.add(PowerShifter.ID);

        //retVal.add(PowerShifter.ID);
/*        retVal.add(Spring.ID);
        retVal.add(Summer.ID);
        retVal.add(Autumn.ID);
        retVal.add(Winter.ID);*/
        //retVal.add(AdaptiveStrikes.ID);
/*        retVal.add(WolfForm.ID);
        retVal.add(WaspSwarmForm.ID);
        retVal.add(OwlForm.ID);
        retVal.add(Roar.ID);*/
        //retVal.add(HummingbirdForm.ID);
        //retVal.add(FoxForm.ID);
        //retVal.add(RockSlide.ID);
/*        retVal.add(RitualOfTheElders.ID);
        retVal.add(RitualOfTheExtinct.ID);
        retVal.add(Pounce.ID);
        retVal.add(BurrBomb.ID);*/
        //retVal.add(ReplenishingNectar.ID);
/*        retVal.add(LionForm.ID);
        retVal.add(MonkeyForm.ID);
        retVal.add(SquirrelForm.ID);*/
        //retVal.add(FoxForm.ID);

        //retVal.add(SpiritOfTheEarth.ID);
        //retVal.add(PhoenixForm.ID);
        //retVal.add(Tornado.ID);
        //retVal.add(IntimidatingAura.ID);
        //retVal.add(EagleForm.ID);

        //retVal.add(EntForm.ID);
        //retVal.add(Flourish.ID);
        //retVal.add(ThornedSkin.ID);
        //retVal.add(TopazSkin.ID);
        //retVal.add(RatForm.ID);
        //retVal.add(PorcupineForm.ID);


        //retVal.add(Wildfire.ID);
/*        retVal.add(ForestFire.ID);
        retVal.add(MagicMushrooms.ID);
        retVal.add(Consume.ID);*/
        //retVal.add(MultiSpiritedStrike.ID);
        //retVal.add(MammothForm.ID);
        //retVal.add(Blossom.ID);
        //retVal.add(RestoreBalance.ID);
        //retVal.add(Absorb.ID);
        //retVal.add(CursedApple.ID);

        //retVal.add(Bonk.ID);
        //retVal.add(HydraForm.ID);
        /*retVal.add(DeepWound.ID);
        retVal.add(LeapingSlash.ID);*/
        //retVal.add(ThrashAbout.ID);
        //retVal.add(MysticalSeed.ID);

        //retVal.add(ShiftingStrike.ID);
        //retVal.add(Sapling.ID);
        //retVal.add(DiverseSpirit.ID);
        //retVal.add(GoldenBerries.ID);
        //retVal.add(BearForm.ID);
        //retVal.add(OpportuneSwoop.ID);
        //retVal.add(ThornedSkin.ID);

        /*retVal.add(FallingLeaves.ID);
        retVal.add(VineHandcuffs.ID);
        retVal.add(BurstingSpore.ID);
        retVal.add(BarkSkin.ID);
        retVal.add(PoisonIvy.ID);*/
        //retVal.add(DeerForm.ID);
        //retVal.add(FesteringBite.ID);
        //retVal.add(FesteringBite.ID);
        //retVal.add(Maul.ID);
        //retVal.add(Guard.ID);
        //retVal.add(Rest.ID);
        //retVal.add(Efflorescence.ID);
        //retVal.add(AdaptiveStrikes.ID);
        //retVal.add(Uproot_Redwood.ID);
        //retVal.add(FlowerShield.ID);
        //retVal.add(Mystical_Branch.ID);
        //retVal.add(Summon_Mountains.ID);
        //retVal.add(Sap_Burst.ID);


        return retVal;
    }

    // Starting Relics
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        //retVal.add(PlaceholderRelic.ID);
        //retVal.add(Golden_Flower.ID);
        retVal.add(TheShapeShiftersMagnolia.ID);
        retVal.add(AnimalHeart.ID);
        //retVal.add(PlaceholderRelic2.ID);
        //retVal.add(DefaultClickableRelic.ID);

        //UnlockTracker.markRelicAsSeen(PlaceholderRelic.ID);
        //UnlockTracker.markRelicAsSeen(Golden_Flower.ID);
        //UnlockTracker.markRelicAsSeen(PlaceholderRelic2.ID);
        //UnlockTracker.markRelicAsSeen(DefaultClickableRelic.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return SHAPESHIFTER_CARD_COLOR;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return SHAPESHIFTER_BROWN;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new ShapeShifter_Strike();
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "The ShapeShifter";
    }

    // Should return a new instance of your character, sending name as its name parameter.
    @Override
    public AbstractPlayer newInstance() {
        return new ShapeShifter(name, chosenClass);
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return SHAPESHIFTER_BROWN;
    }

    // Should return a Color object to be used as screen tint effect when your
    // character attacks the heart.
    @Override
    public Color getSlashAttackColor() {
        return SHAPESHIFTER_BROWN;
    }

    // Should return an AttackEffect array of any size greater than 0. These effects
    // will be played in sequence as your character's finishing combo on the heart.
    // Attack effects are the same as used in DamageAction and the like.
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    // Should return a string containing what text is shown when your character is
    // about to attack the heart. For example, the defect is "NL You charge your
    // core to its maximum..."
    @Override
    public String getSpireHeartText() {
        return "Oi";
    }

    // The vampire events refer to the base game characters as "brother", "sister",
    // and "broken one" respectively.This method should return a String containing
    // the full text that will be displayed as the first screen of the vampires event.
    @Override
    public String getVampireText() {
        return "Oi2";
    }

}
