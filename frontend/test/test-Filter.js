const {describe, it, before, after} = require('mocha')
const path = require('path')
const express = require('express')
const webdriver = require('selenium-webdriver')
require('chromedriver')

const {By, until} = webdriver

describe('Sample Filter', function () {
  let driver
  let server

  this.timeout(60000)

  before((done) => {
    const app = express()
    app.use('/', express.static(path.resolve(__dirname, '../../dist')))
    server = app.listen(8080, done)
  })
  after(() => {
    server.close()
  })

  before(async () => {
    driver = new webdriver.Builder()
      .forBrowser('chrome')
      .build()
  })
  after(async () => await driver.quit())

  it('RB 1', async function () {
    await driver.get('http://localhost:8081/')
    console.log("Connected to server")
    await driver.wait(until.titleIs('Filtering Sample'))
    console.log("Title OK")
    //await driver.wait(until.elementTextIs(await driver.findElement(By.css('.page-header')), ''))
    //console.log("Page is the same")


    const apply = await driver.findElement(By.id('apply'))
    const reset = await driver.findElement(By.id('reset'))
    console.log("Found Apply/Reset buttons")

    const has_photo_yes = await driver.findElement(By.id('has_photo_yes'))
    const has_photo_no = await driver.findElement(By.id('has_photo_no'))
    console.log("Found photo buttons")

    const contact_yes = await driver.findElement(By.id('contact_yes'))
    const contact_no = await driver.findElement(By.id('contact_no'))
    console.log("Found contact buttons")

    const favorite_yes = await driver.findElement(By.id('favorite_yes'))
    const favorite_no = await driver.findElement(By.id('favorite_no'))
    console.log("Found favorite buttons")

    await has_photo_yes.click()
    await contact_no.click()
    await favorite_yes.click()

    await apply.click()

    const elem = await driver.findElement(By.xpath('//*[@id="content"]/div/div[2]/div/div[2]/table/tbody/tr/td[1]'))
    //console.log(elem)
    await driver.wait(until.elementTextIs(elem,'Katlin'))
    console.log("Passed")
  })

  it('RB 1', async function () {
    await driver.get('http://localhost:8081/')
    console.log("Connected to server")
    await driver.wait(until.titleIs('Filtering Sample'))
    console.log("Title OK")
    //await driver.wait(until.elementTextIs(await driver.findElement(By.css('.page-header')), ''))
    //console.log("Page is the same")


    const apply = await driver.findElement(By.id('apply'))
    const reset = await driver.findElement(By.id('reset'))
    console.log("Found Apply/Reset buttons")

    const has_photo_yes = await driver.findElement(By.id('has_photo_yes'))
    const has_photo_no = await driver.findElement(By.id('has_photo_no'))
    console.log("Found photo buttons")

    const contact_yes = await driver.findElement(By.id('contact_yes'))
    const contact_no = await driver.findElement(By.id('contact_no'))
    console.log("Found contact buttons")

    const favorite_yes = await driver.findElement(By.id('favorite_yes'))
    const favorite_no = await driver.findElement(By.id('favorite_no'))
    console.log("Found favorite buttons")

    await has_photo_yes.click()
    await contact_no.click()
    await favorite_yes.click()

    await apply.click()

    const elem = await driver.findElement(By.xpath('//*[@id="content"]/div/div[2]/div/div[2]/table/tbody/tr/td[1]'))
    //console.log(elem)
    await driver.wait(until.elementTextIs(elem,'Katlin'))
    console.log("Passed")
  })

})
