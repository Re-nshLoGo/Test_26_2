package com.example.service;

import com.example.dao.BookRepo;
import com.example.model.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    BookRepo bookRepo;
    public int add(Book book) {
        Book book1 = bookRepo.save(book);
        return book1.getBook_id();
    }

    public JSONArray getbook(String id) {
    JSONArray jsonArray = new JSONArray();
    if(id != null && bookRepo.findById(Integer.valueOf(id)).isPresent()){
        Book book = bookRepo.findById(Integer.valueOf(id)).get();
        if (book != null) {
            JSONObject json = getJsonobj(book);
            jsonArray.put(json);
        }
    }
    else {
        List<Book> books = bookRepo.findAll();
        for (Book book : books) {
            JSONObject json = getJsonobj(book);
            jsonArray.put(json);
        }
    }
            return jsonArray;
    }

        private JSONObject getJsonobj(Book book) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bookId",book.getBook_id());
            jsonObject.put("title",book.getTitle());
            jsonObject.put("author",book.getAuthor());
            jsonObject.put("description",book.getDescription());
            jsonObject.put("price",book.getPrice());
            jsonObject.put("Student",book.getStudent());
            return jsonObject;

        }

    public void update(Book book,String id) {
       if(bookRepo.findById(Integer.valueOf(id)).isPresent()){
           Book oldbook = bookRepo.findById(Integer.valueOf(id)).get();
           oldbook.setStudent(book.getStudent());
           oldbook.setBook_id(book.getBook_id());
           oldbook.setPrice(book.getPrice());
           oldbook.setTitle(book.getTitle());
           oldbook.setAuthor(book.getAuthor());
           oldbook.setDescription(book.getDescription());
           bookRepo.save(oldbook);
       }
    }

    public void delete(String id) {
        if (bookRepo.findById(Integer.valueOf(id)).isPresent()) {
            bookRepo.deleteById(Integer.valueOf(id));
        }
    }
}
