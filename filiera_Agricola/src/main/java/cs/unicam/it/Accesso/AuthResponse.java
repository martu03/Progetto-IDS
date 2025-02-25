package cs.unicam.it.Accesso;

// DTO per risposta di login
class AuthResponse {

        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }