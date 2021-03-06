package StSShapeShifter.actions;

import StSShapeShifter.ShapeshifterMod;
import StSShapeShifter.characters.ShapeShifter;
import basemod.TopPanelGroup;
import basemod.TopPanelItem;
import basemod.helpers.RelicType;
import basemod.patches.com.megacrit.cardcrawl.helpers.TopPanel.TopPanelHelper;
import com.badlogic.gdx.Game;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.RelicAboveCreatureEffect;

public class FoxDigAction extends AbstractGameAction {
    public boolean upgraded;
    public AbstractPlayer owner;
    public FoxDigAction(AbstractPlayer owner, AbstractCreature source, boolean upgraded) {
        this.upgraded = upgraded;
        this.owner = owner;
        this.source = source;
    }

    public void update() {
        //owner.relics.add(AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON));
        int relicRoll = AbstractDungeon.treasureRng.random(0, 99);
        //AbstractDungeon.getRandomChest();
        ShapeshifterMod.logger.info(relicRoll);
        if(upgraded) {
            int goldRoll = AbstractDungeon.treasureRng.random(6, 10);
            ShapeshifterMod.logger.info(goldRoll);
            this.addToBot(new GainGoldAction(goldRoll));
            for(int i = 0; i < goldRoll; ++i) {
                AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.source.hb.cX, this.source.hb.cY, this.source.hb.cX, this.source.hb.cY, true));
            }
            //96
            if(relicRoll > 96) {
                int relicTierRoll = AbstractDungeon.treasureRng.random(0, 99);
                ShapeshifterMod.logger.info(relicTierRoll);
                if (relicTierRoll < 70) {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                    this.addToBot(new RelicAboveCreatureAction(owner, relic));
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                    //owner.relics.add(relic);
                } else if (relicTierRoll < 95) {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.UNCOMMON);
                    this.addToBot(new RelicAboveCreatureAction(owner, relic));
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                } else {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE);
                    this.addToBot(new RelicAboveCreatureAction(owner, relic));
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                }

            }
        }
        else {
            int goldRoll = AbstractDungeon.treasureRng.random(4, 7);
            ShapeshifterMod.logger.info(goldRoll);
            this.addToBot(new GainGoldAction(goldRoll));
            for(int i = 0; i < goldRoll; ++i) {
                AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.source.hb.cX, this.source.hb.cY, this.source.hb.cX, this.source.hb.cY, true));
            }
            if(relicRoll > 98) {
                int relicTierRoll = AbstractDungeon.treasureRng.random(0, 99);
                if (relicTierRoll < 70) {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                } else if (relicTierRoll < 95) {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.UNCOMMON);
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                } else {
                    AbstractRelic relic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE);
                    this.addToBot(new RelicAboveCreatureAction(owner, relic));
                    relic.instantObtain();
                    CardCrawlGame.metricData.addRelicObtainData(relic);
                    relic.flash();
                }
            }
        }
        TopPanelHelper.topPanelGroup.update();
        this.isDone = true;
    }
}
