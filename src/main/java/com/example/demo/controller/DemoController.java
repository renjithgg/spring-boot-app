package com.example.demo.controller;

import com.example.demo.dao.ProductsDao;
import com.example.demo.model.Products;
import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@RestController

public class DemoController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private QuestionRepository questionRepository;
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/")
    public String getMessage() {
        return "Welcome home Renjith";
    }

    @GetMapping(value = "/test")
    public String getTestMessage() {
        return "Its test by Renjith";
    }

    @GetMapping(value = "/read-file-old-java")
    public String readQuestion() throws IOException {
        Resource resource = new ClassPathResource("static/questions.txt");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    @GetMapping(value = "/read-file-stream-file-line")
    public String streamUsingFileLine() throws IOException {
        Resource resource = new ClassPathResource("static/questions.txt");
        Path path = Paths.get(String.valueOf(resource.getFile()));

        StringBuilder resultStringBuilder = new StringBuilder();

        Stream<String> lines = Files.lines(path);
        lines.forEach(line->{
            resultStringBuilder.append(line).append("\n");
        });
        lines.close();

        return resultStringBuilder.toString();
    }

    @GetMapping(value = "/read-file-stream-buffer-reader")
    public String streamUsingBufferedReader() throws IOException {
        Resource resource = new ClassPathResource("static/questions.txt");
        Path path = Paths.get(String.valueOf(resource.getFile()));

        StringBuilder resultStringBuilder = new StringBuilder();

        BufferedReader br = Files.newBufferedReader(path);

        Stream <String> lines = br.lines();
        lines.forEach(line->{
            resultStringBuilder.append(line).append("\n");
        });
        lines.close();

        return resultStringBuilder.toString();
    }

    @GetMapping(value="/add-detail") // Map ONLY GET Requests
    public @ResponseBody
    String addNewQuestion (@RequestParam String title
            , @RequestParam String description) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Question n = new Question();
        n.setTitle(title);
        n.setDescription(description);
        questionRepository.save(n);
        return "Saved";
    }

    @GetMapping(value="/get-detail")
    public @ResponseBody Iterable<Question> getAllQuestions() {
        // This returns a JSON or XML with the questions
        return questionRepository.findAll();
    }

    @GetMapping(value="/get-sample-message-from-service")
    String getSampleMessage() {
        try {
            ProductsDao productListing = new ProductsDao();
            return productListing.getSampleMessage();
        } catch (NullPointerException e) {
            return "Error";
        }
    }

    @GetMapping(value="/product-list")
    public List<Products> getProductListing() {
        try{
            ProductsDao productListing = new ProductsDao();
            return productListing.getProductListing();
        }catch(Exception e){
            return null;
        }
    }

}
