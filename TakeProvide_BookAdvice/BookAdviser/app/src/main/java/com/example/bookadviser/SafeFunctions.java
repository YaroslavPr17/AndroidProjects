package com.example.bookadviser;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.bookadviser.Entities.Author;
import com.example.bookadviser.Entities.Book;
import com.example.bookadviser.Entities.Publisher;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SafeFunctions {
    public static AppDatabase createDatabase(Context context, boolean verbose) {
        class CreateDatabase extends AsyncTask<Void, Void, AppDatabase> {

            @Override
            protected AppDatabase doInBackground(Void... voids) {
                AppDatabase db = DatabaseClient
                        .getInstance(context)
                        .getAppDatabase();
                return db;
            }

            @Override
            protected void onPostExecute(AppDatabase db) {
                super.onPostExecute(db);

                if (verbose) {
                    Toast.makeText(context,
                            "Database was connected", Toast.LENGTH_LONG).show();

                }

            }
        }

        CreateDatabase CD = new CreateDatabase();
        try {
            AppDatabase result = CD.execute().get();
            return result;
        }
        catch (ExecutionException | InterruptedException eie){
            Toast.makeText(context, "Exception while creating database. Aborting.", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public static void insertAuthor(Context context, Author author, AppDatabase db, boolean verbose) {
        class InsertAuthor extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.authorDao().insert(author);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if (verbose){
                    Toast.makeText(context,
                            "Author inserted successfully!", Toast.LENGTH_LONG).show();
                }

            }
        }

        InsertAuthor IA = new InsertAuthor();

        IA.execute();
    }


    public static void insertBook(Context context, Book book, AppDatabase db, boolean verbose) {
        class InsertBook extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.bookDao().insert(book);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if (verbose) {
                    Toast.makeText(context,
                            "Book inserted successfully!", Toast.LENGTH_LONG).show();
                }
            }
        }

        InsertBook IB = new InsertBook();

        IB.execute();
    }


    public static void insertPublisher(Context context, Publisher publisher, AppDatabase db, boolean verbose) {
        class InsertPublisher extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.publisherDao().insert(publisher);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if(verbose){
                    Toast.makeText(context,
                            "Publisher inserted successfully!", Toast.LENGTH_LONG).show();
                }

            }
        }

        InsertPublisher IP = new InsertPublisher();

        IP.execute();
    }


    public static List<Author> getAllAuthors(Context context, AppDatabase db) {
        class GetAllAuthors extends AsyncTask<Void, Void, List<Author>> {

            @Override
            protected List<Author> doInBackground(Void... voids) {
                List<Author> la = db.authorDao().getAll();
                return la;
            }

            @Override
            protected void onPostExecute(List<Author> la) {
                super.onPostExecute(la);
            }
        }

        GetAllAuthors GAA = new GetAllAuthors();
        try {
            List<Author> la = GAA.execute().get();
            return la;
        }
        catch (ExecutionException | InterruptedException eie){
            Toast.makeText(context,
                    "Exception while getting all authors.", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    public static List<Book> getAllBooks(Context context, AppDatabase db) {
        class GetAllBooks extends AsyncTask<Void, Void, List<Book>> {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                List<Book> lb = db.bookDao().getAll();
                return lb;
            }

            @Override
            protected void onPostExecute(List<Book> lb) {
                super.onPostExecute(lb);
            }
        }

        GetAllBooks GAB = new GetAllBooks();
        try {
            List<Book> lb = GAB.execute().get();
            return lb;
        }
        catch (ExecutionException | InterruptedException eie){
            Toast.makeText(context,
                    "Exception while getting all authors.", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    public static List<Publisher> getAllPublishers(Context context, AppDatabase db) {
        class GetAllPublishers extends AsyncTask<Void, Void, List<Publisher>> {

            @Override
            protected List<Publisher> doInBackground(Void... voids) {
                List<Publisher> lp = db.publisherDao().getAll();
                return lp;
            }

            @Override
            protected void onPostExecute(List<Publisher> la) {
                super.onPostExecute(la);
            }
        }

        GetAllPublishers GAP = new GetAllPublishers();
        try {
            List<Publisher> lp = GAP.execute().get();
            return lp;
        }
        catch (ExecutionException | InterruptedException eie){
            Toast.makeText(context,
                    "Exception while getting all authors.", Toast.LENGTH_LONG).show();
        }
        return null;
    }


    public static void deleteAuthor(Context context, Author author, AppDatabase db, boolean verbose) {
        class DeleteAuthor extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.authorDao().delete(author);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if (verbose){
                    Toast.makeText(context,
                            "Author was deleted successfully.", Toast.LENGTH_LONG).show();
                }

            }
        }

        DeleteAuthor DA = new DeleteAuthor();

        DA.execute();
    }

    public static void deleteBook(Context context, Book book, AppDatabase db, boolean verbose) {
        class DeleteBook extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.bookDao().delete(book);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if (verbose) {
                    Toast.makeText(context,
                            "Book was deleted successfully.", Toast.LENGTH_LONG).show();
                }
            }
        }

        DeleteBook DB = new DeleteBook();

        DB.execute();
    }


    public static void deletePublisher(Context context, Publisher publisher, AppDatabase db, boolean verbose) {
        class DeletePublisher extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                db.publisherDao().delete(publisher);
                return null;
            }

            @Override
            protected void onPostExecute(Void a) {
                super.onPostExecute(a);

                if(verbose){
                    Toast.makeText(context,
                            "Publisher was deleted successfully!", Toast.LENGTH_LONG).show();
                }

            }
        }

        DeletePublisher DP = new DeletePublisher();

        DP.execute();
    }


}
