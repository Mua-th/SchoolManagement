package org.example.vue.ElementVue;

import org.example.dao.ElementDao;
import org.example.dao.ElementDaoImpl;
import org.example.services.user.ElementService.ElementService;
import org.example.services.user.ElementService.ElementServiceImpl;

public class ElementMain {
    public static void main(String[] args) {
        ElementDao dao = ElementDaoImpl.getInstance();
        ElementService service = new ElementServiceImpl(dao);
        ElementViewImpl view = new ElementViewImpl(service);
        view.displayMenu();
    }
}
