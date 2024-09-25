export interface AuthContextType {
    isAuthenticated: boolean;
    funcao: string;
    login: (token: string) => void;
    logout: () => void;
}