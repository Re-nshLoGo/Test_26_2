package com.example.controller;

import com.example.dao.StdRepo;
import com.example.model.Book;
import com.example.model.Student;
import com.example.service.BookService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    StdRepo stdRepo;
    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody String reqbookdata){
        Book book = setBookdata(reqbookdata);
        int id =  bookService.add(book);
        return new ResponseEntity<>("Book added id "+id , HttpStatus.CREATED);
    }
    @GetMapping("get-book")
    public ResponseEntity<String> getbook(@Nullable @RequestParam String id){
        JSONArray jsonArray = bookService.getbook(id);
        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }
    @PutMapping("/update-book/{id}")
    public ResponseEntity<String> updateBook(@PathVariable String id , @RequestBody String reqbookdata){
        Book book = setBookdata(reqbookdata);
        bookService.update(book,id);
        return new ResponseEntity<>("Book updated",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable String id){
        bookService.delete(id);
    }

    private Book setBookdata(String reqbookdata) {
        JSONObject jsonObject = new JSONObject(reqbookdata);
        Book book = new Book();
        book.setBook_id(jsonObject.getInt("bookId"));
        book.setTitle(jsonObject.getString("title"));
        book.setAuthor(jsonObject.getString("author"));
        book.setDescription(jsonObject.getString("description"));
        book.setPrice(jsonObject.getInt("price"));
        int stId = jsonObject.getInt("stdId");
        Student std = stdRepo.findById(stId).get();
        book.setStudent(std);
        return book;

    }
}
