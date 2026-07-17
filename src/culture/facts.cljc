(ns culture.facts
  "Country-level regional-culture catalog for Portugal (PRT) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"PRT"
   [{:culture/id "prt.dish.bacalhau"
     :culture/name "Bacalhau"
     :culture/country "PRT"
     :culture/kind :dish
     :culture/summary "Dried salted cod considered the iconic ingredient of Portuguese cuisine, nicknamed \"fiel amigo\" (loyal friend)."
     :culture/url "https://en.wikipedia.org/wiki/Bacalhau"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.dish.pastel-de-nata"
     :culture/name "Pastel de nata"
     :culture/country "PRT"
     :culture/kind :dish
     :culture/summary "Portuguese egg custard tart pastry, optionally dusted with cinnamon."
     :culture/url "https://en.wikipedia.org/wiki/Pastel_de_nata"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.dish.caldo-verde"
     :culture/name "Caldo verde"
     :culture/country "PRT"
     :culture/kind :dish
     :culture/summary "Popular soup in Portuguese cuisine originating from the Minho Province in northern Portugal, traditionally made with julienned Portuguese cabbage, potatoes, olive oil and seasonings."
     :culture/url "https://en.wikipedia.org/wiki/Caldo_verde"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.beverage.port-wine"
     :culture/name "Port wine"
     :culture/name-local "Vinho do Porto"
     :culture/country "PRT"
     :culture/kind :beverage
     :culture/summary "Portuguese fortified wine produced in the Douro Valley of northern Portugal, typically a sweet red wine often served with dessert."
     :culture/url "https://en.wikipedia.org/wiki/Port_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.craft.fado"
     :culture/name "Fado"
     :culture/country "PRT"
     :culture/kind :craft
     :culture/summary "Portuguese music genre traced to 1820s Lisbon, characterized by mournful melodies and themes of fate and longing, designated a UNESCO Intangible Cultural Heritage in 2011."
     :culture/url "https://en.wikipedia.org/wiki/Fado"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.craft.azulejo"
     :culture/name "Azulejo"
     :culture/country "PRT"
     :culture/kind :craft
     :culture/summary "Painted tin-glazed ceramic tilework that constitutes a major aspect of Portuguese architecture, with Portugal distinctively adopting complete wall coverage reflecting Moorish aesthetic principles; the tradition is shared with Spanish architecture."
     :culture/url "https://en.wikipedia.org/wiki/Azulejo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "prt.heritage.jeronimos-monastery"
     :culture/name "Jeronimos Monastery"
     :culture/name-local "Mosteiro dos Jerónimos"
     :culture/country "PRT"
     :culture/kind :heritage
     :culture/summary "In 1983, the Jeronimos Monastery in Lisbon was classified as a UNESCO World Heritage Site, along with the nearby Tower of Belem."
     :culture/url "https://en.wikipedia.org/wiki/Jer%C3%B3nimos_Monastery"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-prt culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "PRT"))
                 " PRT entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
