(ns statute.facts
  "General-law compliance catalog for Portugal (PRT) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL DRE (Diário da República Eletrónico,
  Portugal's official electronic gazette) English-translation URL --
  never fabricated. A law not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url. Unlike
  diariodarepublica.pt's own React-rendered detail/consolidated-law
  pages (which returned empty content to WebFetch, a JS-only failure
  mode), the `files.diariodarepublica.pt/diplomastraduzidos/` and
  `files.dre.pt` PDF hosts rendered fully and were verified by directly
  reading the source PDF text via the Read tool -- each PDF's own header
  states the official gazette series/number and publication date.")

(def catalog
  "iso3 -> vector of statute entries."
  {"PRT"
   [{:statute/id "prt.commercial-companies-code-1986"
     :statute/title "Commercial Companies Code (Código das Sociedades Comerciais)"
     :statute/jurisdiction "PRT"
     :statute/kind :law
     :statute/law-number "Decree-Law no. 262/86"
     :statute/url "https://files.diariodarepublica.pt/diplomastraduzidos/262_1986_CommercialCompaniesCode_EN_pub.pdf?lang=EN"
     :statute/url-provenance :official-dre
     :statute/enacted-date "1986-09-02"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "prt.data-protection-act-2019"
     :statute/title "Lei de execução do RGPD (Data Protection Act)"
     :statute/jurisdiction "PRT"
     :statute/kind :law
     :statute/law-number "Lei n.º 58/2019"
     :statute/url "https://files.dre.pt/1s/2019/08/15100/0000300040.pdf"
     :statute/url-provenance :official-dre
     :statute/enacted-date "2019-08-08"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "prt.labour-code-2009"
     :statute/title "Labour Code (Código do Trabalho)"
     :statute/jurisdiction "PRT"
     :statute/kind :law
     :statute/law-number "Law no. 7/2009"
     :statute/url "https://files.dre.pt/diplomastraduzidos/7_2009_CodigoTrabalho_EN_publ.pdf"
     :statute/url-provenance :official-dre
     :statute/enacted-date "2009-02-12"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-prt statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "PRT")) " PRT statutes seeded with an "
                 "official DRE (Diário da República) citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
