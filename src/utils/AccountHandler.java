package utils;

import constants.Message;
import constants.Path;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import models.Account;

public class AccountHandler extends ArrayList<Account>{

    public void loadFromFile(String path) {
        if(!this.isEmpty()){
            this.clear();
        }
        try{
            File f = new File(path);
            if(!f.exists()){
                throw new IOException(Message.FILE_NOT_FOUND + path);
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Account account;
            try{
                while((account = (Account) fo.readObject()) != null){
                    this.add(account);
                }
            }catch(EOFException e){
                //do nothing
            }
            fo.close();
            fi.close();
//            System.out.println(Message.READ_FILE_SUCCESSFULLY + path);
        }catch(Exception e){
            System.out.println(Message.READ_FILE_FAILED + e.getMessage());
        }
    }

    public void saveToFile(String path) {
        if(this.isEmpty()){
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        try{
            File f = new File(path);
            FileOutputStream fOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(Account account : this){
                out.writeObject(account);
            }
            out.close();
            fOut.close();
//            System.out.println(Message.SAVE_FILE_SUCCESSFULLY + path);
        }catch(Exception e) {
            System.out.println(Message.SAVE_FILE_FAILED + e.getMessage());
        }
    }
}
