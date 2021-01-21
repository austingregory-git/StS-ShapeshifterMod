package StSShapeShifter.events;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.cards.Lynx_Form;
import StSShapeShifter.cards.StormGryphonForm;
import StSShapeShifter.cards.Tortoise_Form;
import StSShapeShifter.cards.UnicornForm;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static StSShapeShifter.ShapeshifterMod.makeEventPath;

public class ViolentStormsEvent extends AbstractImageEvent {


    public static final String ID = ShapeshifterMod.makeID(ViolentStormsEvent.class.getSimpleName());
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath(ViolentStormsEvent.class.getSimpleName() + ".png");

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;

    private float HEALTH_LOSS_PERCENTAGE = 0.15F; // 3%
    private float HEALTH_LOSS_PERCENTAGE_LOW_ASCENSION = 0.10F; // 5%

    private int healthdamage; //The actual number of how much Max HP we're going to lose.

    public ViolentStormsEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);

        if (AbstractDungeon.ascensionLevel >= 15) { // If the player is ascension 15 or above, lose 5% max hp. Else, lose just 3%.
            healthdamage = (int) ((float) AbstractDungeon.player.maxHealth * HEALTH_LOSS_PERCENTAGE);
        } else {
            healthdamage = (int) ((float) AbstractDungeon.player.maxHealth * HEALTH_LOSS_PERCENTAGE_LOW_ASCENSION);
        }

        // The first dialogue options available to us.
        imageEventText.setDialogOption(OPTIONS[0] + healthdamage + OPTIONS[1]);
        imageEventText.setDialogOption(OPTIONS[2]);
        imageEventText.setDialogOption(OPTIONS[3]);
    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        ArrayList<AbstractCard> master = AbstractDungeon.player.masterDeck.group;
        ArrayList<AbstractCard> masterCopy = new ArrayList<>(master);
        Iterator var1 = masterCopy.iterator();
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0: // If you press button the first button (Button at index 0), in this case: Inspiration.
                         // Screen set the screen number to 1. Once we exit the switch (i) statement,
                        // we'll still continue the switch (screenNum) statement. It'll find screen 1 and do it's actions
                        // (in our case, that's the final screen, but you can chain as many as you want like that)
                        AbstractCard c = new StormGryphonForm();
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float)Settings.WIDTH * 0.3F, (float)Settings.HEIGHT / 2.0F));
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)null, healthdamage));
                        while(var1.hasNext()) {
                            AbstractCard card = (AbstractCard) var1.next();
                            if(card.cardID.equals(Lynx_Form.ID) || card.cardID.equals(Tortoise_Form.ID)) {
                                AbstractDungeon.effectList.add(new PurgeCardEffect(card));
                                AbstractDungeon.player.masterDeck.removeCard(card);
                            }
                        }
                        //AbstractDungeon.player.masterDeck.removeCard();
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();

                        return; // Onto screen 1 we go.
                    case 1: // If you press button the second button (Button at index 1), in this case: Deinal
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        while(var1.hasNext()) {
                            AbstractCard card = (AbstractCard) var1.next();
                            if(card.cardID.equals(Lynx_Form.ID)) {
                                AbstractDungeon.effectList.add(new PurgeCardEffect(card));
                                AbstractDungeon.player.masterDeck.removeCard(card);
                            }
                            if(card.cardID.equals(Tortoise_Form.ID) && card.canUpgrade()) {
                                card.upgrade();
                                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * (float)Settings.WIDTH, MathUtils.random(0.2F, 0.8F) * (float)Settings.HEIGHT));

                            }
                        }
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        this.screenNum = 1;


                        // Same as before. A note here is that you can also do
                        // imageEventText.clearAllDialogs();
                        // imageEventText.setDialogOption(OPTIONS[1]);
                        // imageEventText.setDialogOption(OPTIONS[4]);
                        // (etc.)
                        // And that would also just set them into slot 0, 1, 2... in order, just like what we do in the very beginning

                        return; // Onto screen 1 we go.
                    case 2: // If you press button the third button (Button at index 2), in this case: Acceptance

                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        while(var1.hasNext()) {
                            AbstractCard card = (AbstractCard) var1.next();
                            if(card.cardID.equals(Tortoise_Form.ID)) {
                                AbstractDungeon.effectList.add(new PurgeCardEffect(card));
                                AbstractDungeon.player.masterDeck.removeCard(card);
                            }
                            if(card.cardID.equals(Lynx_Form.ID) && card.canUpgrade()) {
                                card.upgrade();
                                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(), MathUtils.random(0.1F, 0.9F) * (float)Settings.WIDTH, MathUtils.random(0.2F, 0.8F) * (float)Settings.HEIGHT));

                            }
                        }
                        this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        this.screenNum = 1;
                        return;
                    default:
                        return;
                }
            case 1: // Welcome to screenNum = 1;
                this.openMap(); // You'll open the map and end the event.
                break;
            default:
                this.openMap();
            }
         }
}
