package com.tarot.tarot.service;

import com.tarot.tarot.model.TarotCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.security.SecureRandom;

@Service
public class TarotServiceImpl implements TarotService {
    private static final Map<Integer, TarotCard> cards = new ConcurrentHashMap<>();

    static {
        // init cards
        try {
            // 使用类加载器获取资源输入流
            InputStream is = TarotServiceImpl.class.getClassLoader().getResourceAsStream("static/tarot_cards.json");
            if (is == null) {
                throw new RuntimeException("Resource file tarot_cards.json not found in static folder");
            }
            ObjectMapper mapper = new ObjectMapper();

            Map<Integer, TarotCard> loadedCards = mapper.readValue(is, new TypeReference<Map<Integer, TarotCard>>() {});
            cards.putAll(loadedCards);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load tarot cards from JSON", e);
        }

//        cards.put(1, new TarotCard("愚者 - Ο", "正位","意味着充满无限的可能性，或许会有出乎意料的机会或灵感降临，所以可以不用过度在意世俗的眼光，依照自己的心意顺其自然过生活。"));
//        cards.put(2,new TarotCard("愚者 - Ο","逆位","迟迟犹豫不决的行为会在近期特别明显，但这样的态度反而会让人有不负责任的感觉，间接失去身旁人们的信任，所以学习如何拿捏自由与社会化之间的平衡，就会是很重要的课题。"));
//
//        cards.put(3,new TarotCard("魔术师 - I","正位","代表内在有明确的想法和目标，不用等待他人支援或准备，就可以积极展开行动。"));
//        cards.put(4,new TarotCard("魔术师 - I","逆位","逆位魔术师所施展的魔法是一种欺骗和诡计，背后代表有可能只想获得利益的自私心态。"));
//
//        cards.put(5,new TarotCard("女祭司 - II","正位","代表拥有智慧、理性以及高洁的精神，不会过度偏袒任何一方或是投机取巧。"));
//        cards.put(6,new TarotCard("女祭司 - II","逆位","代表洁癖或是完美主义的一面会特别显眼，为了排除自己无法接受的部分，甚至可能还会以扭曲的角度面对现实。"));
//
//        cards.put(7,new TarotCard("皇后 - III","正位","代表因为过去累积的努力，无论是精神还是物质上都能获得满足。"));
//        cards.put(8,new TarotCard("皇后 - III","逆位","代表过度的爱与过剩的物资，沉浸在享乐主义之中，慢慢失去对世界万物感恩的心。"));
//
//        cards.put(9,new TarotCard("皇帝 - IV","正位","代表有执行力、说话也有建设性的形象，可以有效领导组织，也能确实尽到自身的责任而获得他人赞赏。"));
//        cards.put(10,new TarotCard("皇帝 - IV","逆位","代表可能有倔强固执或是专断独行的态度，以不合理的方式对待他人。"));
//
//        cards.put(11,new TarotCard("教皇 - V","正位","代表因过去确实遵守规范，而和他人建立起的信任关系，或者是遇见了精神上的榜样，可以在人生旅途上带领你成长。"));
//        cards.put(12,new TarotCard("教皇 - V","逆位","代表有违反伦理道德的迹象，甚至可能有以伪善的行为做出背叛他人的负面形象。"));
//
//        cards.put(13,new TarotCard("恋人 - VI","正位","代表对某件事物深深吸引，所以仿佛置身在天堂般的享受，所以鼓励你去勇敢追求想要的事物。"));
//        cards.put(14,new TarotCard("恋人 - VI","逆位","过度沉溺在眼前的快乐，甚至呈现出现在稳定就好的消极态度，可能会让人有不负责任、很随兴的感觉。"));
//
//        cards.put(15,new TarotCard("战车 - VII","正位","代表无论前方有任何阻碍都不会被打败，并且能够勇往直前朝向目标顺利迈进。"));
//        cards.put(16,new TarotCard("战车 - VII","逆位","代表战车的能量开始失去控制，可能会朝不好的方向前进，也暗示需暂时停下来好好厘清前进的方向。"));
//
//        cards.put(17,new TarotCard("力量 - VIII","正位","代表抱持着真心诚意待人，就能与对方培养信任关系，甚至可能会互相协助一起跨越难关。"));
//        cards.put(18,new TarotCard("力量 - VIII","逆位","代表有毅力不足而无法坚持到最后，或者是过于谄媚或试图利用对方的行为，让人产生出负面的形象。"));
//
//        cards.put(19,new TarotCard("隐士 - IX","正位","代表可以静下心，重新审视内心的模样，甚至追求理想的稳重态度，可能会因而得到众人的尊敬，借此担任领导的职务。"));
//        cards.put(20,new TarotCard("隐士 - IX","逆位","代表过于拘泥追求理想，可能会无法接受眼前的状况而逃避现实。"));
//
//        cards.put(21,new TarotCard("命运之轮 - X","正位","代表正处于幸运的状态之中，可能也会有意料之外的机会到访，但好运往往不会等人，所以若察觉到机会就要采取行动加以掌握。"));
//        cards.put(22,new TarotCard("命运之轮 - X","逆位","代表命运的齿论正逆时针旋转，呈现出不顺遂的状况。可能会出现徒劳无功或是浪费难得的机会。"));
//
//        cards.put(23, new TarotCard("正义 - XI", "正位", "代表面对的事物会受到合理公正的对待，并且过去的所作所为，也可能将会受到公平的审视。"));
//        cards.put(24, new TarotCard("正义 - XI", "逆位", "代表过去的行为，可能受到情感或个人利益考量而有偏颇，也可以解释成自己正受到不合理对待的状态。"));
//
//        cards.put(25, new TarotCard("倒吊人 - XII", "正位", "代表承认现实上的不如意，可以冷静下来好好思考来龙去脉，或许就能借此获得新灵感，将逆境化为转机。"));
//        cards.put(26, new TarotCard("倒吊人 - XII", "逆位", "代表无法接受现状而苦苦挣扎，但在这样的情况下，更需要的是正视问题所在并采取行动做出改变。"));
//
//        cards.put(27, new TarotCard("死神 - XIII", "正位", "代表迈向新阶段的开始，虽然可能会感到不适应，但拥抱改变也意味着有全新的未来在等着你。"));
//        cards.put(28, new TarotCard("死神 - XIII", "逆位", "代表目前依然受到过去的人事物而执着，没办法轻易改变现状所以无法前进下一个人生阶段。"));
//
//        cards.put(29, new TarotCard("节制 - XIV", "正位", "代表面对不同的意见和想法，可以接受并融合自身的意见，与他人加以讨论，从中找出最适合的处理方式。"));
//        cards.put(30, new TarotCard("节制 - XIV", "逆位", "代表无法接受新的意见或想法，可能会经常发生错身而过，或者是与他人交际上的冲突等情况。"));
//
//        cards.put(31, new TarotCard("恶魔 - XV", "正位", "代表无法抗拒各种诱惑，所以任由恶魔占据内心，欲望也随之永无止尽地要求更多。"));
//        cards.put(32, new TarotCard("恶魔 - XV", "逆位", "代表正在和内心的恶魔交战中，试图改掉坏毛病，但试图改正长期以来的习惯并不是一件容易的事，所以需时常检视自己是否又重蹈覆辙。"));
//
//        cards.put(33, new TarotCard("高塔 - XVI", "正位", "代表突如其来的冲击虽然会让人茫然自失，但是勇于面对并适时做出调整，或许借此能夠带来更多的收获和启发。"));
//        cards.put(34, new TarotCard("高塔 - XVI", "逆位", "代表突如其来的意外刚开始可能没那么冲击，但之后会慢慢发酵，让你不得不改变现状，并且从中反思到底是什么原因，让你执着到紧抓着旧事物不放。"));
//
//        cards.put(35, new TarotCard("星星 - XVII", "正位", "代表目前正在朝向理想的目标前进，持续保持下去就会迎向光明的未来。"));
//        cards.put(36, new TarotCard("星星 - XVII", "逆位", "牌被反过来，所以含有希望寓意的星星看起来就像正坠落到地面，代表出于某些原因，导致抱持的希望变成失望，也暗指目标好高骛远，最终可能会达不到理想的结果。"));
//
//        cards.put(37, new TarotCard("月亮 - XVIII", "正位", "因为月亮的光是反射太阳光而成的，所以相较之下会有朦胧不清的感觉，而在塔罗牌的世界，暗指目前可能是透过幻想看着现实，并且内心有想要隐瞒的事物而不安。"));
//        cards.put(38, new TarotCard("月亮 - XVIII", "逆位", "代表逐渐看得清楚现实状况，开始察觉到许多事物的真实面貌。"));
//
//        cards.put(39, new TarotCard("太阳 - XIX", "正位", "代表持续依循想去做的热忱和勇往直前的行动力，就能从中获得喜悦，并且自然而然获得回报。"));
//        cards.put(40, new TarotCard("太阳 - XIX", "逆位", "从逆位牌来看就像是太阳正在西沉，代表无法充分发挥自身的力量，所以最终结果难以符合心中理想的模样。"));
//
//        cards.put(41, new TarotCard("审判 - XX", "正位", "代表能确实把握机会，让过去的事物重新成为下一阶段的人生转折点。"));
//        cards.put(42, new TarotCard("审判 - XX", "逆位", "代表因为曾在关键时刻无法鼓起勇气，所以机会没有被及时掌握而溜走，可能导致重拾的计划或人际关系再度被封印。"));
//
//        cards.put(43, new TarotCard("世界 - XXI", "正位", "代表过去脚踏实地完成事情，所以完成时会产生一种贯彻到底的满足感；或者是你对原有的人事物有了不同的认知，目前已经来到全新的人生境界。"));
//        cards.put(44, new TarotCard("世界 - XXI", "逆位", "虽然已经有一定的完成度，但最终结果却不如预期。"));

    }

    @Override
    public List<TarotCard> drawThreeCards() {
        List<TarotCard> deck = new ArrayList<>(cards.values());
        // Collections.shuffle(deck);
        SecureRandom random = new SecureRandom();
        Collections.shuffle(deck, random);

//        System.out.println("Deck size: " + deck.size());
//        deck.forEach(card -> System.out.println(card));


        return deck.subList(0, 3);
    }
}
