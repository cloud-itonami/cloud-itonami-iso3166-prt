(ns marketentry.facts "Portugal market-entry catalog.")
(def catalog
  {"PRT" {:name "Portugal"
          :owner-authority "IMPIC / BASE / Vortal e-procurement"
          :legal-basis "Código dos Contratos Públicos; EU directives"
          :national-spec "BASE / e-procurement platforms + NIF"
          :provenance "https://www.base.gov.pt/"
          :required-evidence ["NIF record" "e-procurement registration record" "CRC company extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / IMPIC"
          :rep-legal-basis "EU establishment or authorized representative for many procedures"
          :rep-provenance "https://www.base.gov.pt/"
          :corporate-number-owner-authority "Autoridade Tributária / IRN"
          :corporate-number-legal-basis "NIF / NIPC"
          :corporate-number-provenance "https://www.portaldasfinancas.gov.pt/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "ESP" {:name "Spain" :owner-authority "PLACE" :legal-basis "LCSP" :national-spec "PLACSP" :provenance "https://contrataciondelestado.es/"
          :required-evidence ["NIF record" "PLACSP registration" "Registro Mercantil extract" "Authorized-representative record"]}
   "FRA" {:name "France" :owner-authority "PLACE" :legal-basis "Code de la commande publique" :national-spec "PLACE" :provenance "https://www.marches-publics.gouv.fr/"
          :required-evidence ["SIRET record" "PLACE registration" "RCS extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
