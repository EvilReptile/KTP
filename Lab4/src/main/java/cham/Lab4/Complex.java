package cham.Lab4;

public class Complex {
    /**
     * Класс - реализация комплексного числа
     * real - реальная часть комплексного числа
     * imaginary - мнимая часть комплексного числа
     *
     * Так же реализованы get-ры и простейшие мат. операции с комплексным числом
     */
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary){
        this.imaginary = imaginary;
        this.real = real;
    }

    public double real(){
        return real;
    }

    public double imaginary(){
        return imaginary;
    }

    // Метод получения значения комплексного числа
    public double abs(){
        return real * real - imaginary * imaginary;
    }

    // Метод сложения комплексных чисел
    public Complex add(Complex term){
        double real = this.real + term.real();
        double imaginary = this.imaginary + term.imaginary();
        return new Complex(real, imaginary);
    }

    // Метод умножения комплексных чисел
    public Complex mul(Complex multiplier){
        double real = this.real * multiplier.real() - this.imaginary * multiplier.imaginary();
        double imaginary = this.real * multiplier.imaginary() + this.imaginary * multiplier.real();
        return new Complex(real, imaginary);
    }
}
