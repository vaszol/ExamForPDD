package ru.vaszol.exam.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionManager  implements HttpSessionListener {
	 
		@Override
		public void sessionCreated(HttpSessionEvent arg0) {
			synchronized (SESSIONS) {
				SESSIONS.put(arg0.getSession().getId(), arg0.getSession());
			}
		}
	 
		@Override
		public void sessionDestroyed(HttpSessionEvent arg0) {
			synchronized (SESSIONS) {
				SESSIONS.remove(arg0.getSession().getId());
			}
		}
	 
		public static boolean isSessionActive(String sessionId) {
			synchronized (SESSIONS) {
				return SESSIONS.containsKey(sessionId);
			}
		}
	 
		public static void invalidateSession(String sessionId) {
			HttpSession sess = null;
			synchronized (SESSIONS) {
				sess = SESSIONS.get(sessionId);
			}
			if (sess != null)
				sess.invalidate();
		}
	 
		public static int getSessionCount() {
			synchronized (SESSIONS) {
				return SESSIONS.size();
			}
		}
	 
		private static final Map<String, HttpSession> SESSIONS = new HashMap<String, HttpSession>();
}
