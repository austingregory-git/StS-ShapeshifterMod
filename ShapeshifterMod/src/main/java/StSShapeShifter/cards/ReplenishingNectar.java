package StSShapeShifter.cards;

import StSShapeShifter.actions.ReplenishingNectarAction;
import StSShapeShifter.actions.UpgradeHandAction;
import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class ReplenishingNectar extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(ReplenishingNectar.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC2 = 1;
    //private static final int BLOCK = 12;
    //private static final int UPGRADE_PLUS_BLOCK = 3;


    // /STAT DECLARATION/


    public ReplenishingNectar() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.misc = 1;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber = MAGIC;
        this.baseMagicNumber = this.misc;
        this.exhaust = true;
        //this.tags.add(CardTags.STARTER_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ShapeshifterMod.logger.info(this.magicNumber);
        this.applyPowers();
        ShapeshifterMod.logger.info(this.magicNumber);
        this.addToBot(new ReplenishingNectarAction(this.uuid, this.misc, this.defaultSecondMagicNumber));
        ShapeshifterMod.logger.info(this.magicNumber);
        ShapeshifterMod.logger.info(this.baseMagicNumber);
        ShapeshifterMod.logger.info(this.misc);
        ShapeshifterMod.logger.info(this.defaultSecondMagicNumber);
        this.addToBot(new HealAction(p, p, this.baseMagicNumber));
        ShapeshifterMod.logger.info(this.magicNumber);
    }

    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MAGIC2);
            this.rawDescription = "Heal for !M!. Increase this heal by 2 permanently. Exhaust.";
            initializeDescription();
        }
    }
}





