package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class Forgiveness extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(Forgiveness.class.getSimpleName()); // DELETE THIS ONE.

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;

    // /STAT DECLARATION/


    public Forgiveness() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.triggerOnGlowCheck();
        ShapeshifterMod.logger.info(GameActionManager.damageReceivedThisCombat);
        this.applyPowers();
        ShapeshifterMod.logger.info(this.magicNumber);
        ShapeshifterMod.logger.info(this.baseMagicNumber);
        this.addToBot(new HealAction(p, p, this.baseMagicNumber));
        //ShapeshifterMod.logger.info(this.magicNumber);
    }

    public void applyPowers() {
        if(upgraded)
            this.baseMagicNumber = MAGIC + (int)((float)GameActionManager.damageReceivedThisCombat * (50.0F / 100.0F));
        else
            this.baseMagicNumber = MAGIC + UPGRADE_MAGIC + (int)((float)GameActionManager.damageReceivedThisCombat * (30.0F / 100.0F));
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void atTurnStart() {
        if(upgraded)
            this.baseMagicNumber = MAGIC + (int)((float)GameActionManager.damageReceivedThisCombat * (50.0F / 100.0F));
        else
            this.baseMagicNumber = MAGIC + UPGRADE_MAGIC + (int)((float)GameActionManager.damageReceivedThisCombat * (30.0F / 100.0F));
        this.initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}





