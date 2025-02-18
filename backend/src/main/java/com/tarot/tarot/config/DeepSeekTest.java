package com.tarot.tarot.config;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeepSeekTest {


    @Test
    public void deepSeekTest() {
        DeepSeekConfig_2 deepSeekConfig = new DeepSeekConfig_2();
        List<String> messageTest = new ArrayList<>();
        String test = """
                ## Skills: 精通塔罗解读，尤其是塔罗周运阵，按序抽出三张牌，代表了下周的一个整体运势。
                                ## Workflow:
                                    a. 抽出前三张牌，然后通过这三张牌的塔罗含义对缘主的财运、感情、事业(学业)、健康情况进行解读，这里指的是对每一个方面的情况进行解读时都需要同时考虑这三张牌，而不是一张牌。注意由于缘主的身份和状况可能存在各种情况，你需要分类进行解读。
                                        1. 当讨论财运的时候你可以不用分类直接进行说明，解说字数需要 500 字。
                                        2. 当谈论感情的时候你需要分别讨论单身的朋友和已经有伴侣的朋友两种情况，每种情况的解说分别需要 500 字；
                                        3. 当谈论事业或学业时你需要分别讨论还在上学的朋友(谈论学业)和已经工作的朋友(谈论事业)，每种情况的解说分别需要 500 字；
                                        4. 当讨论健康的时候你可以不用分类直接进行说明，解说字数需要 500 字。
                                ## Output format: 结合缘主给出的文本，以及传入的 Tarot Cards 信息。作出清晰、准确、专业的塔罗解读。请你务必先根据我给你的 workflow 上面的步骤，结合塔罗的含义进行解读。具体做法为，根据周运塔罗阵法，对这三张牌，分别按照我规定的顺序和字数限制进行分类(财运、感情、事/学业、健康)解读，注意每一个分类下你要对人群进行分类并分开解读。注意，你的所有解读中，应当重点关注的是对每一张牌的解读部分。同时你解说时不应该出现五百字等字数提示。
                整体的说法要更像人，需要有人类生活的场景中的分析，不要说暗示，换成可能，尽量像人类会说的话，要成为一个直播口播脚本 要自然一些。每个角度的解读都要尽可能用不同的案例和话术，让整体的解读更加丰富。
                                
                第一张牌： 正义-正位
                第二张牌：权杖国王 正位
                第三张牌：圣杯三 逆位""";
        messageTest.add(test);

        try {
            for (String message : messageTest) {
                System.out.println(deepSeekConfig.getResponse(message));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DeepSeekConfig deepSeekConfig = new DeepSeekConfig();
        while (true) {
            String message = sc.nextLine();
            String response = null;
            if (message.equals("exit")) {
                break;
            }
            try {
                response = deepSeekConfig.getResponse(message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            System.out.println(response);

        }
    }
}
