package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class StepDefinitions {

    final static String url = "http://automationpractice.com/";

    final static String mensajeEsperado = "Product successfully added to your shopping cart";

    WebDriver driver = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver, 60);

    String respuestaActual = "";

    WebElement firstResult;

    @Given("que se seleccionó una prenda")
    public void given(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.get(url);
            WebElement element = driver.findElement(By.id("homefeatured")).findElements(By.tagName("li"))
                    .get(0)
                    .findElement(By.className("product-container"))
                    .findElement(By.className("right-block"))
                    .findElement(By.className("product-name"));
            element.click();
        wait.until(presenceOfElementLocated(By.id("add_to_cart")))
                .findElement(By.tagName("button")).click();
        firstResult = wait.until(presenceOfElementLocated(By.tagName("h2")));
    }

    @When("doy clic en el botón agregar al carrito")
    public void when() {
        respuestaActual = firstResult.getAttribute("innerText").trim();
    }

    @Then("la prenda debe quedar agregada al carrito")
    public void then() {
        System.out.println("Respuesta actual, " + respuestaActual);
        Assert.assertEquals(mensajeEsperado, respuestaActual);
    }

    @After
    public void after() {
        driver.quit();
    }

}
