package com.teamtreehouse.blog.model;

import java.util.Date;

public class BlogEntries {
    public static BlogEntry[] load() {
        return new BlogEntry[] {
            new BlogEntry("we-happy", "We happy?", "Now that we know who you are, I know who I am. I'm not a mistake! It all makes sense! In a comic, you know how you can tell who the arch-villain's going to be? He's the exact opposite of the hero. And most times they're friends, like you and me! I should've known way back when... You know why, David? Because of the kids. They called me Mr Glass.", new Date(1473718059000L)),
            new BlogEntry("im-serious-as-a-heart-attack","I'm serious as a heart attack","Well, the way they make shows is, they make one show. That show's called a pilot. Then they show that show to the people who make shows, and on the strength of that one show they decide if they're going to make more shows. Some pilots get picked and become television programs. Some don't, become nothing. She starred in one of the ones that became nothing.", new Date(1475237760000L)),
            new BlogEntry("hold-on-to-your-butts","Hold on to your butts","The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil men. Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness, for he is truly his brother's keeper and the finder of lost children. And I will strike down upon thee with great vengeance and furious anger those who would attempt to poison and destroy My brothers. And you will know My name is the Lord when I lay My vengeance upon thee.", new Date(1476482934000L))
        };
    }
}
