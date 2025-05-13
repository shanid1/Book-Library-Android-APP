package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private SharedPreferences sharedPreferences;
    public static synchronized Utils getInstance(Context context) {
        if(instance != null){

            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }

    }
    private static final String ALL_BOOKS_KEY = "allbooks";
    private static final String FAVOURITE_BOOKS_KEY = "favouriteBooks";
    private static final String ALREADY_READ_KEY = "alreadyRead";
    private static final String CURRENTLY_READING_KEY = "currentlyReading";
    private static final String WANT_TO_READ_KEY = "wantToRead";
    //private static ArrayList<Book> allBooks;
    //private static ArrayList<Book> favorites;
    //private static ArrayList<Book> alreadyRead;
    //private static ArrayList<Book> wantToReadLater;
    //private static ArrayList<Book> currentlyReading;


    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alterDb",Context.MODE_PRIVATE);
        if (getAllBooks() == null) {

            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (getAlreadyRead() == null) {
            editor.putString(ALREADY_READ_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReading() == null) {
            editor.putString(CURRENTLY_READING_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();

        }
        if (getWantToReadLater() == null) {
            editor.putString(WANT_TO_READ_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();

        }
        if (getFavorites() == null) {
            editor.putString(FAVOURITE_BOOKS_KEY,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books =  gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null), type);

        return books;
    }
    public  ArrayList<Book> getFavorites() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books =  gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyRead() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books =  gson.fromJson(sharedPreferences.getString(ALREADY_READ_KEY,null), type);
        return books;
    }

    public  ArrayList<Book> getWantToReadLater() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books =  gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY,null), type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReading() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books =  gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_KEY,null), type);
        return books;
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(
                1, 1344, "The Alchemist", "Paulo Coehlo", R.mipmap.alchemist,
                "A spiritual journey of a shepherd boy.",
                "The Alchemist is a timeless philosophical novel that tells the story of Santiago, a young shepherd living in the rolling hills of Andalusia. Santiago has a recurring dream about a hidden treasure near the Egyptian pyramids, which sets him on a journey of profound self-discovery. Along the way, he encounters a variety of mentors — including a mysterious king, a humble Englishman studying alchemy, and finally, the wise Alchemist who becomes his guide in the desert.\n\n" +
                        "The novel is steeped in mystical symbolism and explores the concept of the 'Personal Legend' — one’s destiny in life. Santiago learns to interpret the omens presented by the universe, trust his heart, and embrace the language of the world. He faces trials that test his resolve, including theft, love, uncertainty, and the harshness of the desert. Each experience serves to purify and shape him, not just as a seeker of treasure, but as a soul in search of meaning.\n\n" +
                        "At its core, *The Alchemist* is not about gold or riches, but about the journey of transformation — learning who you are, what you value, and what it means to truly live. Coelho’s poetic prose invites the reader to reflect on their own journey, dreams, and fears. The book remains a global phenomenon because of its universal themes and inspiring message: when you want something with all your heart, the entire universe conspires to help you achieve it."
        ));

        books.add(new Book(
                2, 279, "To Kill a Mockingbird", "Harper Lee", R.mipmap.tokillambird,
                "A story of justice and race in the Deep South.",
                "*To Kill a Mockingbird* is a deeply moving novel set in the small town of Maycomb, Alabama, during the Great Depression. It follows the life of young Scout Finch as she grows up under the care of her widowed father, Atticus Finch, a principled lawyer known for his wisdom and integrity. The plot centers around Atticus’s defense of Tom Robinson, a black man falsely accused of raping a white woman — a case that exposes the deep racial divides of the American South.\n\n" +
                        "Scout, along with her brother Jem, witnesses the trial and its surrounding events with increasing awareness of the injustice around her. Through her eyes, we see not only the failures of the legal system but also the quiet strength of those who stand up for what’s right. Her innocent yet insightful perspective adds emotional weight to the story, making the racial and social injustices even more jarring.\n\n" +
                        "The novel explores themes of prejudice, courage, compassion, and moral growth. Atticus becomes a moral compass not only for his children but for readers as well. Meanwhile, the subplot involving the mysterious Boo Radley — a reclusive neighbor — reveals deeper truths about human kindness and misunderstanding. Harper Lee’s lyrical prose and layered storytelling have made *To Kill a Mockingbird* an enduring classic that challenges readers to reflect on empathy, justice, and human dignity."
        ));

        books.add(new Book(
                3, 328, "1984", "George Orwell", R.mipmap.nineteeneightyfour,
                "A dystopian novel about surveillance and totalitarianism.",
                "George Orwell’s *1984* is a searing critique of authoritarianism and a powerful warning against the dangers of absolute power. Set in the dystopian future of Oceania, the novel introduces Winston Smith, a low-ranking Party member who secretly harbors thoughts of rebellion against a regime that controls every aspect of life — from history to language, from personal relationships to private thoughts. The Party, led by the all-seeing Big Brother, uses technology, propaganda, and fear to maintain control.\n\n" +
                        "Winston’s growing dissatisfaction leads him to begin a forbidden romance with Julia, another Party worker who shares his discontent. Together, they experience brief moments of freedom and intimacy. But in a world where even thought is monitored — where the concept of 'thoughtcrime' exists — their rebellion cannot go unnoticed. Eventually, they are captured and subjected to horrific psychological torture in the Ministry of Love, where reality itself is distorted until submission becomes the only escape.\n\n" +
                        "*1984* explores complex themes such as censorship, individuality, truth, memory, and resistance. Orwell presents a chilling vision of a world where language is weaponized through 'Newspeak', history is rewritten daily, and the mere act of thinking independently is punished. The novel's grim ending and haunting ideas — such as the phrase 'War is Peace. Freedom is Slavery. Ignorance is Strength.' — remain frighteningly relevant in discussions of state power and civil liberties.\n\n" +
                        "With its haunting atmosphere and razor-sharp insights, *1984* stands as one of the most important and unsettling novels ever written — a literary monument to the fragility of freedom and the terrifying consequences of unchecked control."
        ));

        books.add(new Book(
                4, 310, "The Great Gatsby", "F. Scott Fitzgerald", R.mipmap.gatsby,
                "A tragic tale of wealth, love, and the American Dream.",
                "F. Scott Fitzgerald’s *The Great Gatsby* is a poignant reflection on the American Dream, wealth, and the illusions of love. The story is told through the perspective of Nick Carraway, a Yale graduate who rents a house next to the lavish mansion of the mysterious Jay Gatsby. Gatsby is famous for throwing extravagant parties attended by New York’s elite, but no one seems to know much about the man himself. As Nick is drawn into Gatsby’s world, he discovers that all the opulence is motivated by one desire — to reunite with Daisy Buchanan, Gatsby’s lost love.\n\n" +
                        "Daisy, now married to the brutish and wealthy Tom Buchanan, represents everything Gatsby longs for: status, romance, and the hope of recreating a perfect past. But as the story unfolds, the cracks in this dream begin to show. Gatsby’s attempts to reclaim what he once had only lead to heartbreak and disillusionment, and ultimately, to his tragic demise.\n\n" +
                        "*The Great Gatsby* is more than a love story — it’s a critique of the moral emptiness of the upper class and the idea that money can buy happiness or absolution. Fitzgerald’s writing is lyrical and symbolic, filled with imagery that captures the glittering facade of the 1920s and the hollow pursuit of wealth. The green light across the bay, Gatsby’s parties, and the Valley of Ashes all contribute to a powerful meditation on ambition, illusion, and loss.\n\n" +
                        "As much a tale of longing as it is of despair, Gatsby’s story remains a haunting allegory of the American Dream — a dream corrupted by materialism and false ideals."
        ));

        books.add(new Book(
                5, 500, "Pride and Prejudice", "Jane Austen", R.mipmap.pride,
                "A romantic satire on manners and marriage.",
                "*Pride and Prejudice* is Jane Austen’s most beloved novel, blending romantic tension with sharp social commentary. Set in rural England, it tells the story of Elizabeth Bennet, a strong-willed and intelligent young woman who is determined to marry for love — not wealth or status. When she meets Mr. Darcy, a wealthy but seemingly arrogant gentleman, the two immediately clash. What begins as mutual disdain slowly transforms into admiration as both characters confront their own faults.\n\n" +
                        "Elizabeth’s journey is one of wit, self-reflection, and emotional growth. She navigates the pressures placed on women of her time: economic dependence, inheritance laws, and the demand for socially advantageous marriages. Meanwhile, Darcy’s evolution from prideful aloofness to sincere vulnerability highlights the redemptive power of love and humility.\n\n" +
                        "Austen fills the novel with memorable characters — the frivolous Mrs. Bennet, the charming but deceptive Wickham, and the pompous Mr. Collins — all of whom contribute to the humor and tension of the story. The novel satirizes the rigid social structure of early 19th-century England, while also celebrating the idea that true understanding and affection can overcome societal barriers.\n\n" +
                        "*Pride and Prejudice* is not just a romantic novel — it’s a profound reflection on gender, reputation, and individuality. With its timeless themes, sparkling dialogue, and unforgettable protagonists, it continues to enchant and resonate with readers over two centuries later."
        ));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(null != books) {

            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }

            }
        }
        return null;
    }

    public Boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyRead();

        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_KEY);
                editor.putString(ALREADY_READ_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;

    }
    public Boolean removeAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyRead();

        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_KEY);
                        editor.putString(ALREADY_READ_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadLater();

        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_KEY);
                editor.putString(WANT_TO_READ_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public Boolean removeToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadLater();

        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_KEY);
                        editor.putString(WANT_TO_READ_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();

        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_KEY);
                editor.putString(CURRENTLY_READING_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean removeToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();

        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_KEY);
                        editor.putString(CURRENTLY_READING_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addToFav(Book book) {
        ArrayList<Book> books = getFavorites();

        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS_KEY);
                editor.putString(FAVOURITE_BOOKS_KEY,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean removeToFav(Book book) {
        ArrayList<Book> books = getFavorites();

        if(null!=books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if(books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS_KEY);
                        editor.putString(FAVOURITE_BOOKS_KEY,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
