package exceptions;

import views.RSBBaseView;

/**
 * Created by shambu on 23/11/16.
 */
public class ViewNotDisplayedException extends RuntimeException{

        private RSBBaseView expectedView;

        public ViewNotDisplayedException(RSBBaseView expectedView, Throwable cause) {
            super("Unable to load page: " + expectedView.getPageDescription(), cause);
            this.expectedView = expectedView;
        }

        public ViewNotDisplayedException(RSBBaseView expectedView) {
            super("Unable to load page: " + expectedView.getPageDescription());
            this.expectedView = expectedView;
        }

        public RSBBaseView getExpectedView(){
            return expectedView;
        }
}
