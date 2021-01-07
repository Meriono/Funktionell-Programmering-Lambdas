/**
 * Created by Hanna Edlund
 * Date: 2021-01-04
 * Time: 12:49
 * Project: Inlämning
 */

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return questions.stream().collect(Collectors.toList()).size();
    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        return (int) questions.stream().filter(s -> s.getCategory().equals(category)).count();
    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){
        return questions.stream().map(Question::getQuestionString).collect(Collectors.toList());
    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        return questions.stream().filter(s -> s.getCategory().equals(category))
                .map(Question::getQuestionString).collect(Collectors.toList());
    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){
        List<String> temp = new ArrayList<>();
        questions.forEach(q -> temp.addAll(q.getAllAnswers()));
        return temp.stream().distinct().collect(Collectors.toList());
    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        List<String> temp = new ArrayList<>();
        questions.forEach(q -> temp.addAll(q.getAllAnswers()));
        return temp.stream().anyMatch(a -> a.equalsIgnoreCase(answerCandidate));
    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){
        List<String> temp = new ArrayList<>();
        questions.forEach(q -> temp.addAll(q.getAllAnswers()));
        return (int) temp.stream().filter(a -> a.equalsIgnoreCase(answerCandidate)).count();

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        List<String> temp = new ArrayList<>();
        questions.stream().filter(s -> s.getCategory().equals(c)).forEach(q -> temp.addAll(q.getAllAnswers()));
        return temp.stream().reduce("", (acc, e) -> {
            if(acc.length() < e.length())
                return e;
            return acc;
        });
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

    }

}
