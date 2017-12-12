package com.mbk.application;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/")
@Theme("valo")
public class MainView extends UI {

  private static final long serialVersionUID = 9012475955417294714L;

  private MenuItem home;
  private MenuItem actions;

  @Override
  protected void init(VaadinRequest request) {
    VerticalLayout main = new VerticalLayout();

    Navigator nav = new Navigator(this, main);
    Command command = new Command() {

      private static final long serialVersionUID = -7161202850724750302L;

      @Override
      public void menuSelected(MenuItem selectedItem) {
        if (selectedItem == home) {
          nav.navigateTo("");
        } else if (selectedItem == actions) {
          nav.navigateTo("");
        }
      }
    };

    MenuBar mb = new MenuBar();
    home = mb.addItem("Home", command);
    actions = mb.addItem("Actions", command);

    VerticalLayout v = new VerticalLayout(mb, main);
    setContent(v);
  }
}
