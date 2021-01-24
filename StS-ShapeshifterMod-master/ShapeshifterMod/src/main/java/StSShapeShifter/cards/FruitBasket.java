package StSShapeShifter.cards;

import StSShapeShifter.cards.tempCards.Cherry;
import StSShapeShifter.cards.tempCards.Star;
import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.AllFruit;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;

import java.util.ArrayList;
import java.util.Random;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class FruitBasket extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(FruitBasket.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private static final int UPGRADE_PLUS_MAGIC = 1;


    // /STAT DECLARATION/


    public FruitBasket() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.defaultBaseSecondMagicNumber = 1;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
        ArrayList<AbstractCard> fruit = new ArrayList<AbstractCard>(AllFruit.getAllFruitCards());
        this.cardsToPreview = fruit.get(new Random().nextInt(fruit.size()));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> fruit = new ArrayList<AbstractCard>(AllFruit.getAllFruitCards());
        ArrayList<AbstractCard> cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat;
        //only get cherry once out of a fruit basket
        //there is definitely a better way than this lmao it is 2am and brain is being fishy
        for(AbstractCard c: cardsPlayed) {
            if(c.cardID.equals(Cherry.ID)) {
                fruit = new ArrayList<>(AllFruit.getAllNonHealingFruitCards());
            }
        }
        for(int i = 0; i<this.magicNumber; i++) {
            AbstractCard c = fruit.get(new Random().nextInt(fruit.size()));
            this.addToBot(new MakeTempCardInHandAction(c, 1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}


