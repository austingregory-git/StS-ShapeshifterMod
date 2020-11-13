package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

public class RitualOfTheExtinct extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = ShapeshifterMod.makeID(RitualOfTheExtinct.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 2;
    private static final int UPGRADE_BASE_COST = 1;


    // /STAT DECLARATION/


    public RitualOfTheExtinct() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.type == AbstractMonster.EnemyType.NORMAL)
            this.addToBot(new DamageAction(m, new DamageInfo(p, m.maxHealth, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        if(m.type == AbstractMonster.EnemyType.ELITE)
            this.addToBot(new DamageAction(m, new DamageInfo(p, m.maxHealth/2, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));
        if(m.type == AbstractMonster.EnemyType.BOSS)
            this.addToBot(new DamageAction(m, new DamageInfo(p, m.maxHealth/4, damageTypeForTurn), AbstractGameAction.AttackEffect.LIGHTNING));

        p.decreaseMaxHealth(this.magicNumber);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}





