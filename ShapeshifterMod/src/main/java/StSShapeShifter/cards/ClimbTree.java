package StSShapeShifter.cards;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.actions.ModifyMagicAction;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.powers.DodgePower;
import StSShapeShifter.util.AllFruit;
import StSShapeShifter.util.BloomCountUtils;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class ClimbTree extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(ClimbTree.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    // /STAT DECLARATION/


    public ClimbTree() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = 1;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        if(BloomCountUtils.getBloomCount() >= -3 && BloomCountUtils.getBloomCount() <= 3) {
            this.addToBot(new GainBlockAction(p, 4));
            this.addToBot(new ApplyPowerAction(p, p, new DodgePower(p, this.magicNumber)));
        }
        if(BloomCountUtils.getBloomCount() <= -10) {
            if(upgraded) {
                AbstractCard fl = new FallingLeaves().makeCopy();
                fl.upgrade();
                this.addToBot(new MakeTempCardInHandAction(fl));
            }
            else {
                this.addToBot(new MakeTempCardInHandAction(new FallingLeaves().makeCopy()));
            }

        }
        if(BloomCountUtils.getBloomCount() >= 10) {
            ArrayList<AbstractCard> fruit = new ArrayList<AbstractCard>(AllFruit.getAllFruitCards());
            if(upgraded)
                this.addToBot(new MakeTempCardInHandAction(fruit.get(new Random().nextInt(fruit.size())), 2));
            else
                this.addToBot(new MakeTempCardInHandAction(fruit.get(new Random().nextInt(fruit.size())), 1));

        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}


