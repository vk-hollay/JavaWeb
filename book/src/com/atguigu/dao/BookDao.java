package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @author Hollay
 * @create 2020-11-20-22:15
 * @description
 */
public interface BookDao {
// 在此处按 ctrl + shift + T 自动生成测试类BookDaoTest

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    /**
     * 根据 id 查找图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询总记录数
     * @return
     */
    public Integer queryForPageTotalCount();

    /**
     * 分页查询
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems(int begin, int pageSize);

    public Integer queryForPageTotalCountByPrice(int min, int max);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

}
