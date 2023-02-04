export interface UserDTO {
    name?:string;
    lastName?:string;
    email:string;
    password:string;
}

export interface Credential {
    email: string;
    password: string;
}