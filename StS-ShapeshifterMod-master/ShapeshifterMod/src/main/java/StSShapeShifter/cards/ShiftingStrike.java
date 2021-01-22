package StSShapeShifter.cards;

import StSShapeShifter.characters.ShapeShifter;
import StSShapeShifter.util.AllForms;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import StSShapeShifter.ShapeshifterMod;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

import static StSShapeShifter.ShapeshifterMod.makeCardPath;

//public class ${NAME} extends AbstractDynamicCard {}

public class ShiftingStrike extends AbstractDynamicCard {

    // TEXT DECLARATION

    //public static final String ID = ShapeshifterMod.makeID(${NAME}.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String ID = ShapeshifterMod.makeID(ShiftingStrike.class.getSimpleName()); // DELETE THIS ONE.
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("${NAME}.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = ShapeShifter.Enums.SHAPESHIFTER_CARD_COLOR;

    private static final int COST = 1;  // COST = ${COST}
    private static final int UPGRADED_COST = 1; // UPGRADED_COST = ${UPGRADED_COST}

    private static final int DAMAGE = 9;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    // /STAT DECLARATION/


    public ShiftingStrike() {
        super(ID, ShapeshifterMod.imgFromId(ID), COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.tags.add(CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void triggerOnGlowCheck() {
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && (AllForms.getAllForms().contains(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).cardID))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            this.setCostForTurn(-9);
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            if(this.ModifiedCostCode == 0) {
                this.setCostForTurn(this.cost);
            }

        }

    }

    /*public void triggerOnOtherCardPlayed(AbstractCard card) {
        if(AllForms.getAllForms().contains(card.cardID)) {
            this.setCostForTurn(-9);
            this.addToTop(new VFXAction(new BorderFlashEffect(Color.GOLD, true), 0.1F));
        }
    }*/

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}



